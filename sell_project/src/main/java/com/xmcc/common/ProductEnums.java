package com.xmcc.common;

import lombok.Getter;

/**
 * 商品状态枚举类:
 */
@Getter
public enum ProductEnums {

    PRODUCT_UP(0,"正常"),
    PRODUCT_DOWN(1,"商品下架"),
    PRODUCT_NOT_ENOUGH(10,"商品库存不足"),
    PRODUCT_ERROR(1,"参数异常"),
    NOT_EXITS(1,"商品不存在");

    private int code;
    private String msg;

    ProductEnums(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

}
