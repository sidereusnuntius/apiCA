package com.ca.apiCA.controller;

import com.ca.apiCA.model.User;
import com.ca.apiCA.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(path = "/new")
    public ResponseEntity<String> insert(@RequestBody @Validated User user) {
        userService.insert(user);
        return ResponseEntity.ok().build();
    }
}
