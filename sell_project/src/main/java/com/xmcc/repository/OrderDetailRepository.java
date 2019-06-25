package com.xmcc.repository;

import com.xmcc.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 订单项repository接口
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {


}
