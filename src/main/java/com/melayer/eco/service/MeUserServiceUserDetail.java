/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.melayer.eco.service;

import com.melayer.eco.db.MeMongoFactoryMultiTenenacy;
import com.melayer.eco.model.MeUser;
import com.melayer.eco.repository.MeUserRepo;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author melayer
 */
@Service
public class MeUserServiceUserDetail implements UserDetailsService {

    @Autowired
    private MeUserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("Web security User Name is - "+username);
        
        MeUser user = null;
        
        if (username != null && username.length() > 0) {
            
            user = userRepository.findByUserName(username);
            System.out.println("----> User Name - " + user.getUserName());
            System.out.println("----> Password - " + user.getPassword());

            if (user == null) {

                throw new UsernameNotFoundException(username);
            }
        }
        else{
            System.out.println("In Else Block user service details");
            user = new MeUser();
            user.setUserName("anonymous");
            user.setPassword("anonymous");
            user.addRole("ROLE_ANONYMOUS");
        }

        return new MeUserRepositoryUserDetails(user);
    }

    private final static class MeUserRepositoryUserDetails extends MeUser implements UserDetails {

        public MeUserRepositoryUserDetails(MeUser user) {
            super(user);

        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return getRoles();
        }

        @Override
        public String getUsername() {
            return getUserName();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
