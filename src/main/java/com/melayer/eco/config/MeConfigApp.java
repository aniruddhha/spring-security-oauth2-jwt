/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.melayer.eco.config;

import com.melayer.eco.filter.MeFilterCors;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CorsFilter;

/**
 *
 * @author aniruddha
 */
@Configuration
public class MeConfigApp {
    
   @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public FilterRegistrationBean corsFilterRegistrationBean() {

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();

        MeFilterCors filterCors = new MeFilterCors();
        
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setFilter(filterCors);
        filterRegistrationBean.setOrder(FilterRegistrationBean.HIGHEST_PRECEDENCE);

        return filterRegistrationBean;
    }

    @Bean
    public UndertowEmbeddedServletContainerFactory undertowServletContainerFactory() {

        UndertowEmbeddedServletContainerFactory factory = new UndertowEmbeddedServletContainerFactory();
        factory.setPort(8282);
        factory.setContextPath("/melayer");
        return factory;
    }
    
    @Bean
    public Map<String, Object> mapEntity(){
        
        Map<String,Object> map = new HashMap<>();
        
        return map;
    }
}
