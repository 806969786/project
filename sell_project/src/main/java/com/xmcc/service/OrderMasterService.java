package com.xmcc.service;

import com.xmcc.common.ResultResponse;
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
}
