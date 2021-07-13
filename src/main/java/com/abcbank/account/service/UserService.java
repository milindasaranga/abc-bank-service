package com.abcbank.account.service;

import com.abcbank.account.model.User;
import org.apache.tomcat.websocket.AuthenticationException;

import java.util.Optional;

public interface UserService {

    /**
     *
     * @param username
     * @return
     */
    public Optional<User> getUserByUsername(String username);

    /**
     *
     * @param user
     * @throws AuthenticationException
     */
    public void saveUser(User user) throws AuthenticationException;
}
