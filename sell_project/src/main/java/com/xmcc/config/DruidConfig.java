package com.xmcc.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.google.common.collect.Lists;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置Druid检测sql 配置类
 */
@Configuration //表示该类为配置类
public class DruidConfig {

    @Bean(value = "druidDataSource",initMethod = "init",destroyMethod = "close") //该方法的返回值作为bean交给Spring管理，value：bean的名字，*Method创建与销毁的方法
    @ConfigurationProperties(prefix = "spring.druid") //对应yml Spring：druid
    public DruidDataSource druidDataSource(){
        DruidDataSource druidDataSource=new DruidDataSource();
        druidDataSource.setProxyFilters(Lists.newArrayList(statFilter()));
        return druidDataSource;
    }

    /**
     * 过滤器配置
     * @return
     */
    @Bean
    public StatFilter statFilter(){
        StatFilter statFilter=new StatFilter();
        statFilter.setLogSlowSql(true);//慢查询是否记录日志
        statFilter.setSlowSqlMillis(5);//慢查询时间，指定超过多少时间是慢查询
        statFilter.setMergeSql(true);//格式化sql
        return statFilter;
    }

    /**
     * druid监控平台，输入http:localhost:8888/druid即可访问平台
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        return new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
    }

}
