/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.melayer.eco.model;

import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 *
 * @author melayer
 */
public class MeUser {

    @Id
    private String id;

    private String userName;

    private String password;
    
    private Set<SimpleGrantedAuthority> roles = new HashSet<>();
    
    public MeUser() {
    }

    public MeUser(MeUser user) {

        this.id = user.getId();
        this.userName = user.getUserName();
        this.password = user.getPassword();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void addRole(String role){

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role);
        roles.add(simpleGrantedAuthority);
    }

    public Set<SimpleGrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Set<SimpleGrantedAuthority> roles) {
        this.roles = roles;
    }
}
