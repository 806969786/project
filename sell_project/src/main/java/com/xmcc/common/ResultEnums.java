package com.xmcc.common;

import lombok.Getter;

/**
 * 返回值枚举类:
 *      返回值接口都会用到成功失败等相同的数据，所以我们把常用的提成枚举类，增加系统可扩展性
 */
@Getter //这里是公共的定义 只需要get方法就可以了
public enum ResultEnums {
    SUCCESS(0,"成功"),
    FAIL(1,"失败");

    private int code;
    private String msg;

    ResultEnums(int code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
