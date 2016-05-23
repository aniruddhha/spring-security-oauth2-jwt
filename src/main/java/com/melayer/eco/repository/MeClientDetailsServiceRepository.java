/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.melayer.eco.repository;

import com.melayer.eco.model.MeClientDetails;
import java.io.Serializable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author aniruddha
 */
@Repository
public interface MeClientDetailsServiceRepository extends MongoRepository<MeClientDetails, String>{
    
    
}
