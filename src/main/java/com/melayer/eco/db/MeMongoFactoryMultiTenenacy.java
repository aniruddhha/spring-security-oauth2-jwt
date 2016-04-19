/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.melayer.eco.db;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 *
 * @author aniruddha
 */
public class MeMongoFactoryMultiTenenacy extends SimpleMongoDbFactory{
    
    
    private ThreadLocal<String> threadLocalDbNames = new ThreadLocal<>();
    
    public MeMongoFactoryMultiTenenacy(MongoClient mongoClient, String databaseName) {
        super(mongoClient, databaseName);
    }

    @Override
    public DB getDb() throws DataAccessException {
        
        
        String dataBaseName = (threadLocalDbNames.get()!=null && threadLocalDbNames.get().length() > 0) ? threadLocalDbNames.get() : "ecokrypt";
        setDataBase(dataBaseName);
        System.out.println("Mongofactory Database Name - "+threadLocalDbNames.get());
        return super.getDb(dataBaseName); 
    }
    
    public void setDataBase(String dataBaseName){
        threadLocalDbNames.set(dataBaseName);
    }
}
