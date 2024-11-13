package com.ca.apiCA.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.apiCA.model.Message;
import com.ca.apiCA.repository.MessageRepository;

@Service
public class MessageService {
    @Autowired
    private MessageRepository repository;

    public void save(Message message) {
        repository.save(message);    
    }

    public List<Message> getByDate(String date) {
        return repository.findByDate(date);
    }

    public List<Message> findByAuthor(String username) {
        return repository.findByUserId(username);
    }
}
