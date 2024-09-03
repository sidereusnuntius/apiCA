package com.ca.apiCA.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/test")
public class Test {
    @GetMapping
    public ResponseEntity<String> testar() {
        return new ResponseEntity<>("Olá, mundo!", HttpStatus.OK);
    }
}
