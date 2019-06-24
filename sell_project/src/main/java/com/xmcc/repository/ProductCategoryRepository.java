package com.xmcc.repository;

import com.xmcc.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 商品类别repository接口
 * JpaRepository<对应实体类,主键类型>
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {


}
