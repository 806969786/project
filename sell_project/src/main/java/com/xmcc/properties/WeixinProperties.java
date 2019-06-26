package com.xmcc.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信支付配置文件
 */
@Component
@Data
@ConfigurationProperties(prefix = "wechat")//配置文件获取前缀为wechat
public class WeixinProperties {

    private String appid; //公众号的唯一标识
    private String secret; //公众号的appsecret
}
