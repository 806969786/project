package com.xmcc.util;

import java.math.BigDecimal;

/**
 * 金额计算工具类
 */
public class BigDecimalUtil {
    //new BigDecimal("")//只能字符串  其他的类型都需要valueOf

    /**
     * 相加
     * @param b1
     * @param b2
     * @return
     */
    public static BigDecimal add(double b1, double b2){
        BigDecimal bigDecimal = BigDecimal.valueOf(b1);
        BigDecimal bigDecima2 = BigDecimal.valueOf(b2);
        return bigDecimal.add(bigDecima2);
    }
    public static BigDecimal add(BigDecimal b1,BigDecimal b2){

        return b1.add(b2);
    }

    /**
     * 相乘
     * @param price
     * @param quantity
     * @return
     */
    public static BigDecimal multi(BigDecimal price,Integer quantity){
        BigDecimal bigDecimal = BigDecimal.valueOf(quantity);
        return price.multiply(bigDecimal);
    }

    /**
     * 比较
     * @param orderAmount
     * @param bigDecimal
     * @return
     */
    public static boolean equals2(BigDecimal orderAmount, BigDecimal bigDecimal) {
        return orderAmount.compareTo(bigDecimal)==0;
    }


}