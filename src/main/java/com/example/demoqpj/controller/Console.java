package com.example.demoqpj.controller;

import com.example.demoqpj.entity.Book;
import com.example.demoqpj.entity.BookRepository;
import com.example.demoqpj.util.RandomUtil;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class Console {
    @Autowired
    BookRepository bookRepository;
    public void init(){
        Book book;
        for(int i=0;i<5;i++){
            book = new Book();
            book.name = "book name "+i;
            book.author = RandomUtil.getRandomString(10);
            book.detail = RandomUtil.getRandomString(10)+" "+RandomUtil.getRandomString(10)+" "+RandomUtil.getRandomString(10);
            bookRepository.save(book);
        }
    }
    public void doDynomics(){

        String tableName = "myFirst";
        Map<String, AttributeValue> itemValues = new HashMap<>();
        itemValues.put("idm", AttributeValue.builder().s("1123").build());
        itemValues.put("test", AttributeValue.builder().s("hahaha").build());
        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(itemValues)
                .build();
    }
    public static void main(String[] args) {
        Region region = Region.AP_SOUTHEAST_1;
        DynamoDbClient dynamoDbClient = DynamoDbClient.builder()
                .region(region)
                .build();
        String tableName = "myFirst";
        Map<String, AttributeValue> itemValues = new HashMap<>();
        itemValues.put("idm", AttributeValue.builder().n("1123").build());
        itemValues.put("test", AttributeValue.builder().s("hahaha").build());
        itemValues.put("nameBack", AttributeValue.builder().n("1233321").build());
        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(itemValues)
                .build();
        try {
            // Create the BatchWriteItemRequest.
            PutItemResponse response = dynamoDbClient.putItem(request);
            System.out.println(tableName + " was successfully updated. The request id is "
                    + response.responseMetadata().requestId());

            // Process the response.
           // System.out.println("Batch write successful: " + batchWriteItemResponse);

        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
