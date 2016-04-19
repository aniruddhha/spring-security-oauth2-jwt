/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.melayer.eco.multitenancy;

import com.melayer.eco.db.MeMongoFactoryMultiTenenacy;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author aniruddha
 */
public class MultitenencyInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private DefaultTokenServices defaultTokenService;

    @Autowired
    private MeMongoFactoryMultiTenenacy mongoDbFactory;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("In Interceptor");
        Boolean returnStatus = true;

        printRequestData(request);

        useDatabaseUsingRequestUrl(request);

        return returnStatus;
    }

    private void useDatabaseUsingAccessToken(HttpServletRequest request) {

        String accessToken = request.getHeader("Authorization").substring(7);

        if (accessToken != null) {

            System.out.println("Access Token is - " + accessToken);
            String clientId = defaultTokenService.getClientId(accessToken);
            System.out.println("Client Id is - >" + defaultTokenService.getClientId(accessToken));
            if (clientId != null) {
                mongoDbFactory.setDataBase(defaultTokenService.getClientId(accessToken));
            }
        }
    }

    private void useDatabaseUsingRequestUrl(HttpServletRequest request) {

        System.out.println("Remote ->" + request.getRemoteHost());
        System.out.println("Remote Address -> " + request.getRemoteAddr());
        System.out.println("Remote User -> " + request.getRemoteUser());
        System.out.println("Request URI -> " + request.getRequestURI());
        System.out.println("Request URL -> " + request.getRequestURL().toString());
        System.out.println("Server Name -> " + request.getServerName());
        
        if(request.getRemoteAddr().equals("192.168.61.23")){
            
            mongoDbFactory.setDataBase("university");
        }
        else {
            mongoDbFactory.setDataBase("ecokrypt");
        }
    }

    private void printRequestData(HttpServletRequest request) {

        String password = request.getParameter("password");
        String userName = request.getParameter("username");
        String grantType = request.getParameter("grant_type");
        String scope = request.getParameter("scope");
        String clientSecret = request.getParameter("client_secret");
        String clientIdReq = request.getParameter("client_id");

        System.out.println(" Pass - " + password);
        System.out.println("User Name - " + userName);
        System.out.println("Grant Type - " + grantType);
        System.out.println("Scope - " + scope);
        System.out.println("Client Secret - " + clientSecret);
        System.out.println("Client id - " + clientIdReq);

        String authHeadre = request.getHeader("Authorization");
        System.out.println("Auth Headre " + authHeadre);
    }
}
