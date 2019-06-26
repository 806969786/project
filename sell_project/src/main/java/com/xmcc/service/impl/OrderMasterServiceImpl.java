package com.xmcc.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xmcc.common.*;
import com.xmcc.dto.*;
import com.xmcc.entity.OrderDetail;
import com.xmcc.entity.OrderMaster;
import com.xmcc.entity.ProductInfo;
import com.xmcc.exception.CustomException;
import com.xmcc.repository.OrderMasterRepository;
import com.xmcc.service.OrderDetailService;
import com.xmcc.service.OrderMasterService;
import com.xmcc.service.ProductInfoService;
import com.xmcc.util.BigDecimalUtil;
import com.xmcc.util.IDUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 订单service实现类
 */
@Service("orderMasterService")
public class OrderMasterServiceImpl implements OrderMasterService {

    @Resource
    private ProductInfoService productInfoService;

    @Resource
    private OrderDetailService orderDetailService;

    @Resource
    private OrderMasterRepository orderMasterRepository;

    /**
     * 生成订单，保存订单
     * @param orderMasterDto
     * @return
     */
    @Override
    @Transactional //增删改触发事务
    public ResultResponse insertOrder(OrderMasterDto orderMasterDto) {
        //取出订单项
        List<OrderDetailDto> items=orderMasterDto.getItems();
        //创建订单项集合来存储orderDetail单个订单项
        List<OrderDetail> orderDetailList= Lists.newArrayList();
        //初始化订单的总金额  涉及到钱的都用 高精度计算
        BigDecimal totalPrice=new BigDecimal("0");

        //遍历订单项，获取商品详情 生成订单项集合
        for (OrderDetailDto orderDetailDto:items){
            //查询订单
            ResultResponse<ProductInfo> productInfoResultResponse= productInfoService.queryById(orderDetailDto.getProductId());
            //判断productInfoResultResponse的code
            // 说明该商品未查询到 生成订单失败，因为这儿涉及到事务 需要抛出异常 事务机制是遇到异常才会回滚
            if (productInfoResultResponse.getCode()== ResultEnums.FAIL.getCode()){
                throw new CustomException(productInfoResultResponse.getMsg());
            }
            //得到商品
            ProductInfo productInfo=productInfoResultResponse.getData();
            //比较库存 库存不足 订单生成失败 直接抛出异常 事务才会回滚
            if(productInfo.getProductStock()<orderDetailDto.getProductQuantity()){
                throw new CustomException(ProductEnums.PRODUCT_NOT_ENOUGH.getMsg());
            }
            //创建订单项
            OrderDetail orderDetail = OrderDetail.builder().detailId(IDUtils.createIdbyUUID()).productIcon(productInfo.getProductIcon())
                    .productId(orderDetailDto.getProductId()).productName(productInfo.getProductName())
                    .productPrice(productInfo.getProductPrice()).productQuantity(orderDetailDto.getProductQuantity())
                    .build();
            orderDetailList.add(orderDetail);
            //减少商品库存
            productInfo.setProductStock(productInfo.getProductStock()-orderDetailDto.getProductQuantity());
            productInfoService.updateProduct(productInfo);
            //累加计算订单总价格
            totalPrice = BigDecimalUtil.add(totalPrice,BigDecimalUtil.multi(productInfo.getProductPrice(),orderDetailDto.getProductQuantity()));
        }

        //生成订单id
        String orderId = IDUtils.createIdbyUUID();
        //构建订单信息  日期等都用默认的即可
        OrderMaster orderMaster = OrderMaster.builder().buyerAddress(orderMasterDto.getAddress()).buyerName(orderMasterDto.getName())
                .buyerOpenid(orderMasterDto.getOpenid()).orderStatus(OrderEnum.NEW.getCode())
                .payStatus(PayEnum.WAIT.getCode()).buyerPhone(orderMasterDto.getPhone())
                .orderId(orderId).orderAmount(totalPrice).build();
        //通过流遍历 将生成的订单id，设置到订单项中
        List<OrderDetail> detailList = orderDetailList.stream().map(orderDetail -> {
            orderDetail.setOrderId(orderId);
            return orderDetail;
        }).collect(Collectors.toList());

        //批量插入订单项
        orderDetailService.batchInsert(detailList);
        //保存订单
        orderMasterRepository.save(orderMaster);

        HashMap<String, String> map = Maps.newHashMap();
        //按照前台要求的数据结构传入订单ID
        map.put("orderId",orderId);
        return ResultResponse.success(map);
    }

    /**
     * 根据buyerOpenId分页查询订单_修改时间降序
     * @param orderDto
     * @return
     */
    @Override
    public ResultResponse orderList(OrderDto orderDto) {
        List<OrderMaster> orderMasterList=orderMasterRepository.findAllByBuyerOpenid(orderDto.getOpenid(),(orderDto.getPage()-1)*orderDto.getSize(),orderDto.getSize());
        if(CollectionUtils.isEmpty(orderMasterList)){
            return ResultResponse.fail("该用户还没有订单");
        }
        return ResultResponse.success(orderMasterList);
    }

    /**
     * 查询订单详情
     * @param orderDetailParticipationDto
     * @return
     */
    @Override
    public ResultResponse findOrderDetail(OrderDetailParticipationDto orderDetailParticipationDto) {
        List<OrderDetail> orderDetailList=orderDetailService.findByOrderId(orderDetailParticipationDto.getOrderId());
        OrderMaster orderMaster=orderMasterRepository.findAllByBuyerOpenidAndOrderId(orderDetailParticipationDto.getOpenid(),orderDetailParticipationDto.getOrderId());
        OrderDetailReturnDto orderDetailReturnDto=OrderDetailReturnDto.builder()
                .orderDetailList(orderDetailList)
                .orderAmount(orderMaster.getOrderAmount())
                .orderId(orderMaster.getOrderId())
                .orderStatus(orderMaster.getOrderStatus())
                .buyerAddress(orderMaster.getBuyerAddress())
                .buyerName(orderMaster.getBuyerName())
                .buyerOpenid(orderMaster.getBuyerOpenid())
                .buyerPhone(orderMaster.getBuyerPhone())
                .createTime(orderMaster.getCreateTime())
                .payStatus(orderMaster.getPayStatus())
                .updateTime(orderMaster.getUpdateTime()).build();
        return ResultResponse.success(orderDetailReturnDto);
    }

    /**
     * 取消订单
     * @param orderDetailParticipationDto
     * @return
     */
    @Override
    @Transactional
    public ResultResponse cancelOrder(OrderDetailParticipationDto orderDetailParticipationDto) {
        Integer i=orderMasterRepository.cancel(orderDetailParticipationDto.getOpenid(),orderDetailParticipationDto.getOrderId());
        if(i>0){
            return ResultResponse.success();
        }else {
            throw new CustomException(ResultEnums.FAIL.getMsg());
        }

    }
}
