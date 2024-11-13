package com.ca.apiCA.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ca.apiCA.model.Message;

public interface MessageRepository extends MongoRepository<Message, Integer>{
    List<Message> findByDate(String date);
    List<Message> findByUserId(String id);
}
