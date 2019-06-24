package com.xmcc.repository;

import com.xmcc.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 商品repository接口
 * JpaRepository<对应实体类,主键类型>
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    /**
     * 根据类目编号 查询正常上架的商品
     * @param status 状态
     * @param categoryList 商品类别集合
     * @return 商品信息集合
     */
    public List<ProductInfo> findByProductStatusAndCategoryTypeIn(Integer status,List<Integer> categoryList);
}
