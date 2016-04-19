/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.melayer.eco.config;

import com.melayer.eco.multitenancy.MultitenencyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author aniruddha
 */
@Configuration
public class MeConfigWeb extends WebMvcConfigurerAdapter{

    @Bean
    public MultitenencyInterceptor multitenencyInterceptor(){
        
        return new MultitenencyInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       // super.addInterceptors(registry); //To change body of generated methods, choose Tools | Templates.
       
       registry.addInterceptor(multitenencyInterceptor());
    }
}
