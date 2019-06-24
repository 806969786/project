package com.xmcc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xmcc.entity.ProductInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品实体Dto 可序列化
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfoDto implements Serializable {
    //@JsonProperty 此注解用于属性上，作用是把该属性的名称序列化为另外一个名称
    @JsonProperty("id")
    private String productId;

    /** 名字. */
    @JsonProperty("name")
    private String productName;

    /** 单价. */
    @JsonProperty("price")
    private BigDecimal productPrice;


    /** 描述. */
    @JsonProperty("description")
    private String productDescription;

    /** 小图. */
    @JsonProperty("icon")
    private String productIcon;

    /**
     * 转换成Dto
     *  一般情况都是根据数据库查询到productInfo来构建这个类
     */
    public static ProductInfoDto build(ProductInfo productInfo){
        ProductInfoDto productInfoDto = new ProductInfoDto();
        //复制属性
        BeanUtils.copyProperties(productInfo,productInfoDto);
        return productInfoDto;

    }

}
