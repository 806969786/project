package com.xmcc.exception;

import com.xmcc.common.ResultEnums;
import lombok.Getter;

/**
 * 自定义异常类
 */
@Getter
public class CustomException extends RuntimeException{

    private int  code;

    public CustomException() {
        super();
    }

    /**
     *
     * @param code 错误代码
     * @param message 错误信息
     */
    public CustomException(int code,String message){
        super(message);
        this.code = code;
    }

    /**
     * 错误代码：1
     * @param message 错误信息
     */
    public CustomException(String message){
        this(ResultEnums.FAIL.getCode(),message);
    }

}
