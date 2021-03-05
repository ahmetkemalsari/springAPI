package com.udemy.myProject.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.udemy.myProject.Errors.ApiError;
import com.udemy.myProject.Helper.Views;
import com.udemy.myProject.Managers.UserServiceManager;
import com.udemy.myProject.Models.User;
import com.udemy.myProject.Repositories.UserRepository;
import com.udemy.myProject.Shared.GenericResponse;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;


@RestController
@RequestMapping("/api/1.0")
public class AuthController {

    @Autowired
    UserRepository repository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @RequestMapping(method = RequestMethod.POST,value = "/auth")
    @JsonView(Views.Base.class)
    public ResponseEntity<?> createUser(@RequestHeader(name = "Authorization",required = false) String authorization){
        if(authorization == null){
            ApiError error = new ApiError(401,"Unauthorized request","/api/1.0/auth");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
        String base64encoded = authorization.split("Basic ")[1];
        String decoded = new String(Base64.getDecoder().decode(base64encoded));
        String[] parts = decoded.split(":");
        String username = parts[0];
        String password = parts[1];
        User inDB = repository.findByusername(username);
        if(inDB == null){
            ApiError error = new ApiError(401,"Unauthorized request","/api/1.0/auth");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
        String hashedPassword = inDB.getPassword();
        if(!passwordEncoder.matches(password,hashedPassword)){
            ApiError error = new ApiError(401,"Unauthorized request","/api/1.0/auth");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }

        return ResponseEntity.ok().body(inDB);
    }
}
