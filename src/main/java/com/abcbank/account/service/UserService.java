package com.abcbank.account.service;

import com.abcbank.account.model.User;

import java.util.Optional;

public interface UserService {

    public Optional<User> getUserByUsername(String username);
}
