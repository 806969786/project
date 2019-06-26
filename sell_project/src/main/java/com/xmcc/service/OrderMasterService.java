package com.xmcc.service;

import com.xmcc.common.ResultResponse;
import com.xmcc.dto.OrderDetailParticipationDto;
import com.xmcc.dto.OrderDto;
import com.xmcc.dto.OrderMasterDto;

/**
 * 订单service接口
 */
public interface OrderMasterService {

    /**
     * 生成订单，保存订单
     * @param orderMasterDto
     * @return
     */
    ResultResponse insertOrder(OrderMasterDto orderMasterDto);

    /**
     * 根据buyerOpenId分页查询订单_修改时间降序
     * @param orderDto
     * @return
     */
    public ResultResponse orderList(OrderDto orderDto);

    /**
     * 查询订单详情
     * @param orderDetailParticipationDto
     * @return
     */
    public ResultResponse findOrderDetail(OrderDetailParticipationDto orderDetailParticipationDto);

    /**
     * 取消订单
     * @param orderDetailParticipationDto
     * @return
     */
    public ResultResponse cancelOrder(OrderDetailParticipationDto orderDetailParticipationDto);
}
