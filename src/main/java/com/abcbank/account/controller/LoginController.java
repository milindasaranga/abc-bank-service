package com.abcbank.account.controller;

import com.abcbank.account.dto.AuthRequest;
import com.abcbank.account.dto.AuthResponse;
import com.abcbank.account.service.UserService;
import com.abcbank.account.service.impl.AuthUserServiceImpl;
import com.abcbank.account.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@RestController
//need to be specific as per the deployment host
@CrossOrigin(origins = "*")
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private AuthUserServiceImpl authUserService;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityUtil securityUtil;

    /**
     *
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest request) throws Exception {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect Username or Password", e);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        final UserDetails userDetails = authUserService.loadUserByUsername(request.getUsername());
        final String jwt = securityUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}
