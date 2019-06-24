package com.xmcc.service;

import com.xmcc.common.ResultResponse;
import com.xmcc.dto.ProductCategoryDto;

import java.util.List;

/**
 * 商品类别service接口
 */
public interface ProductCategoryService {

    /**
     *  查询所有商品类别
     * @return
     */
    public ResultResponse<List<ProductCategoryDto>> findAll();
}
