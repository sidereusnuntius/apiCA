package com.ca.apiCA.service;

import com.ca.apiCA.model.User;
import com.ca.apiCA.repository.UserRepository;
import com.ca.apiCA.service.exception.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public User findByUsername(String username) {
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new NotFound("Nome de usuário não encontrado."));

        return user;
    }

    public void insert(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
    }
}
