/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.melayer.eco.repository;

import com.melayer.eco.model.MeUser;
import java.io.Serializable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author melayer
 */
@Repository
public interface MeUserRepo extends MongoRepository<MeUser, String>{
   
    MeUser findByUserName(String userName); 
}
