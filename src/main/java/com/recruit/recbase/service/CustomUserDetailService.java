package com.recruit.recbase.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.recruit.recbase.security.Recruiter;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomUserDetailService implements UserDetailsService{

    @Autowired
    private MongoClient mongoClient;

    @Autowired
    private DynamoDBMapper dynamoDBMapper;


//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        MongoDatabase database = mongoClient.getDatabase("springsecurity");
//        MongoCollection<Document> collection = database.getCollection("users");
//        Document document = collection.find(Filters.eq("email",email)).first();
//        if(document!=null) {
//            String name = document.getString("name");
//            String surname = document.getString("surname");
//            String password = document.getString("password");
//            List<String> authorities = (List<String>) document.get("authorities");
//            Recruiter recruiter = new Recruiter(email,password,authorities.toArray(new String[authorities.size()]));
//            return recruiter;
//        }
//        return null;
//    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Recruiter recruiter = dynamoDBMapper.load(Recruiter.class, email);

        return recruiter;

    }

}
