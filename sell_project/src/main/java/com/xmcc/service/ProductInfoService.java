package com.xmcc.service;

import com.xmcc.common.ResultResponse;
import com.xmcc.entity.ProductInfo;

/**
 * 商品service接口
 */
public interface ProductInfoService {

    /**
     * 查询所有商品信息
     * @return
     */
    public ResultResponse queryList();

    /**
     * 根据Id查询商品信息
     * @param productId
     * @return
     */
    public ResultResponse<ProductInfo> queryById(String productId);

    /**
     * 更新商品信息
     * @param productInfo
     */
    public void updateProduct(ProductInfo productInfo);

}
