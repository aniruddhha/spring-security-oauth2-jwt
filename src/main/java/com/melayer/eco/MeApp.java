/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.melayer.eco;

import com.melayer.eco.db.MeMongoFactoryMultiTenenacy;
import com.melayer.eco.model.MeArticle;
import com.melayer.eco.model.MeUser;
import com.melayer.eco.repository.MeRepoArticle;
import com.melayer.eco.repository.MeUserRepo;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SpringBootWebSecurityConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author melayer
 */
@SpringBootApplication
public class MeApp extends SpringBootServletInitializer{

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(MeApp.class, args);

        for (String name : context.getBeanDefinitionNames()) {

            System.out.println("-> " + name);
        }

        MeMongoFactoryMultiTenenacy factory = context.getBean(MeMongoFactoryMultiTenenacy.class);
        factory.setDataBase("melayer");
           
    }
}
