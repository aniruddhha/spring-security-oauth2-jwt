/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.melayer.eco.config;

import com.melayer.eco.db.MeMongoFactoryMultiTenenacy;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 *
 * @author aniruddha
 */
@Configuration
public class MeConfigDb {

    @Bean
    public MeMongoFactoryMultiTenenacy multitenenatMongoFactoty() throws Exception{

        MeMongoFactoryMultiTenenacy multitenenatMongoFactoty = new MeMongoFactoryMultiTenenacy(new MongoClient(), "ecokrypt");

        return multitenenatMongoFactoty;
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception{

        return new MongoTemplate(multitenenatMongoFactoty());
    }
}
