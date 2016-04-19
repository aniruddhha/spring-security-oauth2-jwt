/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.melayer.eco.filter;

import java.io.IOException;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 *
 * @author aniruddha
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MeFilterCors implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        
        System.out.println("in filter");
        
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        printRequestData(request);
        
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
        
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            System.out.println("in if block in filter working");
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            System.out.println("Else block in filter working");
            chain.doFilter(request, response);
        }
        //chain.doFilter(request, res);
    }

    @Override
    public void destroy() {
    }
    
     private void printRequestData(HttpServletRequest request){
        
        String password = request.getParameter("password");
        String userName = request.getParameter("username");
        String grantType = request.getParameter("grant_type");
        String scope = request.getParameter("scope");
        String clientSecret = request.getParameter("client_secret");
        String clientIdReq = request.getParameter("client_id");
        
        System.out.println("CORS Pass - "+password);
        System.out.println("CORS User Name - "+userName);
        System.out.println("CORS Grant Type - "+grantType);
        System.out.println("CORS Scope - "+scope);
        System.out.println("CORS Client Secret - "+clientSecret);
        System.out.println("CORS Client id - "+clientIdReq);
        
        String authHeadre = request.getHeader("Authorization");
        System.out.println("CORS Auth Headre "+authHeadre);
    }
    
}
