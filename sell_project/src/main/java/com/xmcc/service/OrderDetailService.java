package com.xmcc.service;

import com.xmcc.entity.OrderDetail;

import java.util.List;

/**
 * 订单项service接口
 */
public interface OrderDetailService {

    /**
     * 批量插入订单项
     * @param orderDetailList
     */
    public void batchInsert(List<OrderDetail> orderDetailList);
}
