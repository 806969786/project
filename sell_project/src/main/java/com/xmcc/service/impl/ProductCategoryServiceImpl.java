package com.xmcc.service.impl;

import com.xmcc.common.ResultResponse;
import com.xmcc.dto.ProductCategoryDto;
import com.xmcc.entity.ProductCategory;
import com.xmcc.repository.ProductCategoryRepository;
import com.xmcc.service.ProductCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品类别service实现类
 */
@Service("productCategoryService")
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Resource
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public ResultResponse<List<ProductCategoryDto>> findAll() {
        List<ProductCategory> productCategoryList = productCategoryRepository.findAll();
        //利用流转换为dto集合
        return ResultResponse.success(productCategoryList.stream().map(productCategory ->
                ProductCategoryDto.build(productCategory)
        ).collect(Collectors.toList()));
    }
}
