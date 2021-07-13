package com.abcbank.account.service.impl;

import com.abcbank.account.model.User;
import com.abcbank.account.repository.UserRepository;
import com.abcbank.account.service.UserService;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public Optional<User> getUserByUsername(String username) {
        Optional<User>  user = userRepo.findByUsername(username);
        user.ifPresent(u -> u.setPassword(null));
        return user;
    }

    @Override
    public void saveUser(User user) throws AuthenticationException {
        if (getUserByUsername(user.getUsername()).isPresent()) {
            throw new AuthenticationException("Username already taken");
        }
        if (getUserByEmail(user.getEmail()).isPresent()) {
            throw new AuthenticationException("Email is already taken");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String hash = encoder.encode(user.getPassword());
        user.setPassword(hash);

        userRepo.save(user);
    }

    private Optional<User> getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}
