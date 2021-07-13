package com.abcbank.account.controller;

import com.abcbank.account.service.UserService;
import com.abcbank.account.util.SecurityUtil;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.abcbank.account.model.User;

import java.util.Optional;

@RestController
//need to be specific as per the deployment host
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityUtil securityUtil;

    /**
     *
     * @param token
     * @return
     */
    @GetMapping
    public Optional<User> getUsersById(@RequestHeader("Authorization") String token) {
        String username = securityUtil.extractUsername(token.substring(7));
        return userService.getUserByUsername(username);
    }

    /**
     *
     * @param user
     * @throws AuthenticationException
     */
    @PostMapping("/add")
    public void addUser(@RequestBody User user) throws AuthenticationException {
        userService.saveUser(user);
    }

}
