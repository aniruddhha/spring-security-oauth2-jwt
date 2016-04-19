/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.melayer.eco.controller;

import com.melayer.eco.db.MeMongoFactoryMultiTenenacy;
import com.melayer.eco.model.MeArticle;
import com.melayer.eco.repository.MeRepoArticle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author melayer
 */
@RestController
public class MeController {

    @Autowired
    private MeRepoArticle repoArticle;
    
    @Autowired
    private MeMongoFactoryMultiTenenacy factoryMultitenanecy;
            
    @RequestMapping(method = RequestMethod.GET, value = "/check", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getcheck() {

        //String s=new String("Success");
        ResponseEntity<String> entity = new ResponseEntity<>("Success ok   ", HttpStatus.OK);

        return entity;
    }
    
    @RequestMapping(value = "/articles",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,Object>> listAllArticles(){
        
        ResponseEntity<Map<String,Object>> entity = null;
        
        Map<String,Object> mapEntity = new HashMap<>();
        mapEntity.put("articles", repoArticle.findAll());
        
        entity = new ResponseEntity<>(mapEntity, HttpStatus.OK);
        return entity;
    }
    
    @RequestMapping(value = "/saveToUniversity",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,Object>> addArticleToUniversity(@RequestBody MeArticle article){
        
        ResponseEntity<Map<String,Object>> entity = null;
        
        factoryMultitenanecy.setDataBase("university");
        repoArticle.save(article);
        
        Map<String,Object> mapEntity = new HashMap<>();
        mapEntity.put("status", "success");
        mapEntity.put("msg", "saved to university");
        
        entity = new ResponseEntity<>(mapEntity,HttpStatus.OK);
        
        return entity;
    }
    
    @RequestMapping(value = "/saveToMeLayer",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,Object>> addArticleToMeLayer(@RequestBody MeArticle article){
        
        ResponseEntity<Map<String,Object>> entity = null;
        
        factoryMultitenanecy.setDataBase("ecokrypt");
        repoArticle.save(article);
        
        Map<String,Object> mapEntity = new HashMap<>();
        mapEntity.put("status", "success");
         mapEntity.put("msg", "saved to melayer");
         
         entity = new ResponseEntity<>(mapEntity,HttpStatus.OK);
        
        return entity;
    }

    @RequestMapping(value = "/article",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MeArticle> article(){
        
        MeArticle article = new MeArticle();
        article.setArticleAuthor("Aniruddha");
        article.setArticleDescription("any description");
        article.setArticleName("sample");
        article.setCategory("sample category");
        article.setId("654654654");
        
        ResponseEntity<MeArticle> entity = new ResponseEntity<>(article,HttpStatus.OK);
        
        return entity;
    }
}
