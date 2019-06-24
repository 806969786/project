package com.xmcc.service.impl;

import com.xmcc.common.ResultEnums;
import com.xmcc.common.ResultResponse;
import com.xmcc.dto.ProductCategoryDto;
import com.xmcc.dto.ProductInfoDto;
import com.xmcc.entity.ProductInfo;
import com.xmcc.repository.ProductInfoRepository;
import com.xmcc.service.ProductCategoryService;
import com.xmcc.service.ProductInfoService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品service实现类
 */
@Service("productInfoService")
public class ProductInfoServiceImpl implements ProductInfoService {

    @Resource
    private ProductCategoryService productCategoryService;

    @Resource
    private ProductInfoRepository productInfoRepository;

    @Override
    public ResultResponse queryList() {
        ResultResponse<List<ProductCategoryDto>> categoryServiceResult = productCategoryService.findAll();
        List<ProductCategoryDto> categorydtoList = categoryServiceResult.getData();
        if(CollectionUtils.isEmpty(categorydtoList)){
            return categoryServiceResult;//如果分类列表为空 直接返回了
        }
        //获得类目编号集合
        List<Integer> categoryTypeList = categorydtoList.stream().map(categorydto -> categorydto.getCategoryType()).collect(Collectors.toList());
        //查询商品列表  这里商品上下架可以用枚举 方便扩展
        List<ProductInfo> productInfoList = productInfoRepository.findByProductStatusAndCategoryTypeIn(ResultEnums.PRODUCT_UP.getCode(), categoryTypeList);
        //多线程遍历categorydtoList 取出每个商品类目编号对应的 商品列表 设置进入类目中
        List<ProductCategoryDto> finalResultList = categorydtoList.parallelStream().map(categorydto -> {
            //将productInfo 设置到foods中
            //过滤：每个type进行对应的封装
            //将productInfo转换成ProductInfoDto
            //.collect(Collectors.toList())：将数据收集进一个列表(Stream 转换为 List，允许重复值，有顺序)
            categorydto.setProductInfoDtoList(productInfoList.stream().
                    filter(productInfo -> productInfo.getCategoryType() == categorydto.getCategoryType()).map(productInfo ->
                    ProductInfoDto.build(productInfo)).collect(Collectors.toList()));
            return categorydto;
        }).collect(Collectors.toList());
        return ResultResponse.success(finalResultList);
    }
}
