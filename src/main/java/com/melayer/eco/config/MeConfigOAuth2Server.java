/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.melayer.eco.config;

import com.melayer.eco.db.MeMongoFactoryMultiTenenacy;
import com.melayer.eco.service.MeClientDetailsService;
import com.sun.corba.se.impl.oa.poa.AOMEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 *
 * @author aniruddha
 */
@Configuration
@EnableAuthorizationServer
public class MeConfigOAuth2Server extends AuthorizationServerConfigurerAdapter {

    @Value("sining-key:9t79wfksdfh9w87t98w7trwt79wr8")
    private String siningKey;
    
    @Autowired
    private MeClientDetailsService serviceClientDetails;
    
    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {

        final JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(siningKey);

        return jwtAccessTokenConverter;
    }

    @Bean
    public TokenStore tokenStore() {

        final JwtTokenStore tokenStore = new JwtTokenStore(jwtAccessTokenConverter());

        return tokenStore;
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenStore(tokenStore());
        return tokenServices;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //super.configure(clients); 

        //clients.withClientDetails(userDetailsService);
        
        /*clients.inMemory()
                    .withClient("ecokrypt")
                    .authorizedGrantTypes("password", "authorization_code", "refresh_token","client_credentials")
                    .scopes("read", "write", "trust")
                    .autoApprove("ecokrypt")
                    .authorities("USER", "ADMIN")
                    .accessTokenValiditySeconds(60000).secret("12345")
                .and()
                    .withClient("university")
                    .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                    .scopes("read", "write", "trust")
                    .autoApprove("university")
                    .authorities("USER", "ADMIN")
                    .accessTokenValiditySeconds(60000).secret("678910")
                .and()
                    .withClient("ano")
                    .authorizedGrantTypes("implicit")
                    .authorities("ROLE_CLIENT")
                    .scopes("read", "write", "trust")
                    .autoApprove(true);*/
        clients.withClientDetails(serviceClientDetails);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //super.configure(endpoints); 

        endpoints.tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .accessTokenConverter(jwtAccessTokenConverter())
                .userDetailsService(userDetailsService);
        
    }

   @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .tokenKeyAccess("isAnonymous() || hasRole('ROLE_TRUSTED_CLIENT')") // permitAll()
                .checkTokenAccess("hasRole('TRUSTED_CLIENT')"); // isAuthenticated()
    }
}
