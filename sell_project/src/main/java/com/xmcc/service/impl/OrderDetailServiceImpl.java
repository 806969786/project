package com.xmcc.service.impl;

import com.xmcc.dao.impl.BatchDaoImpl;
import com.xmcc.entity.OrderDetail;
import com.xmcc.entity.OrderMaster;
import com.xmcc.repository.OrderDetailRepository;
import com.xmcc.service.OrderDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单项service实现类
 */
@Service("orderDetailService")
public class OrderDetailServiceImpl extends BatchDaoImpl<OrderDetail> implements OrderDetailService {

    @Resource
    private OrderDetailRepository orderDetailRepository;

    @Override
    @Transactional //增删改触发事务
    public void batchInsert(List<OrderDetail> orderDetailList) {
        super.batchInsert(orderDetailList);
    }

    @Override
    public List<OrderDetail> findByOrderId(String orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }
}
