package com.ca.apiCA.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ca.apiCA.model.Message;
import com.ca.apiCA.service.MessageService;
import com.ca.apiCA.service.UserService;

@RestController
@RequestMapping(path = "/msg")
public class MessageController {
    @Autowired
    MessageService service;
    @Autowired
    UserService userService;
    
    @GetMapping("/all")
    public ResponseEntity<List<Message>> getAllByUser(Principal principal) {
        return ResponseEntity.ok().body(
            service.findByAuthor(principal.getName())
        );
    }

    @PostMapping
    public ResponseEntity<?> create(Principal principal,
        @RequestBody Message msg) {
        msg.setUserId(principal.getName());
        service.save(msg);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Message>> getByDate(@RequestParam(name = "date") String date) {
        return ResponseEntity.ok().body(service.getByDate(date));
    }
}
