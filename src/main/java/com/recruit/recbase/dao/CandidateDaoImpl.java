package com.recruit.recbase.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.recruit.recbase.model.CandidateCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CandidateDaoImpl implements CandidateDao {


    @Autowired
    DynamoDBMapper dynamoDBMapper;

    @Override
    public void saveCandidate(CandidateCard candidateCard){
        dynamoDBMapper.save(candidateCard);
    }

    @Override
    public List<CandidateCard> findAll(){
        return dynamoDBMapper.scan(CandidateCard.class, new DynamoDBScanExpression());
    }

    @Override
    public CandidateCard findOne(String id){
        return dynamoDBMapper.load(CandidateCard.class, id);
    }

    @Override
    public List<CandidateCard> findByName(String name) {
        DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression();
        dynamoDBScanExpression.addFilterCondition("firstName",  new Condition()
                .withComparisonOperator(ComparisonOperator.LT)
                .withAttributeValueList(new AttributeValue().withN(name)));
        return dynamoDBMapper.scan(CandidateCard.class, new DynamoDBScanExpression());
    }

    @Override
    public CandidateCard assigneeRecruiter(String id, String name) {
        CandidateCard cc = dynamoDBMapper.load(CandidateCard.class, id);
        cc.setAssigneeRecruiter(name);
        dynamoDBMapper.save(cc);
        return cc;
    }
}
