package com.abcbank.account.service.impl;

import com.abcbank.account.model.User;
import com.abcbank.account.repository.UserRepository;
import com.abcbank.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    public Optional<User> getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
