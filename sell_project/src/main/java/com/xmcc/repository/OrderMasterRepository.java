package com.xmcc.repository;

import com.xmcc.entity.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 订单Repository接口
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String>, JpaSpecificationExecutor<OrderMaster> {

    /**
     * 根据buyerOpenId分页查询订单_修改时间降序
     * @param buyerOpenId 卖家微信ID
     *                    start 起始记录数
     *                    size 每页大小
     * @return
     */
    @Query(value="SELECT * FROM order_master WHERE buyer_openid=?1 ORDER BY update_time DESC LIMIT ?2,?3",nativeQuery=true)
    public List<OrderMaster> findAllByBuyerOpenid(String buyerOpenId,Integer start,Integer size);

    /**
     * 根据BuyerOpenidAndOrderId查询订单
     * @param buyer_openid
     * @param order_id
     * @return
     */
    public OrderMaster findAllByBuyerOpenidAndOrderId(String buyer_openid, String order_id);

    /**
     * 取消订单
     * @param openid
     * @param orderId
     * @return
     */
    @Query(value="update order_master set order_status=2 WHERE buyer_openid=?1 and order_id=?2",nativeQuery=true)
    @Modifying
    public Integer cancel(String openid, String orderId);

}
