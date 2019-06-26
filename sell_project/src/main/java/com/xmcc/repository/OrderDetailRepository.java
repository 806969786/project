package com.xmcc.repository;

import com.xmcc.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 订单项repository接口
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

    /**
     * 根据orderId查询订单项详情
     * @param orderId
     * @return
     */
    public List<OrderDetail> findByOrderId(String orderId);
}
