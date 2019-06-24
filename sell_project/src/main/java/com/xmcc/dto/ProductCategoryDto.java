package com.xmcc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xmcc.entity.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 商品类别实体Dto 可序列化
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryDto implements Serializable {
    /** 类目名字.
     * @JsonProperty 此注解用于属性上，作用是把该属性的名称序列化为另外一个名称
     * */
    @JsonProperty("name")
    private String categoryName;

    /** 类目编号. */
    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods") //防止为null时被忽略
    public List<ProductInfoDto> productInfoDtoList;

    /**
     * 转换成Dto
     */
    public static ProductCategoryDto build(ProductCategory productCategory){
        ProductCategoryDto productCategoryDto = new ProductCategoryDto();
        BeanUtils.copyProperties(productCategory,productCategoryDto);
        return productCategoryDto;
    }
}
