package com.udemy.myProject.Managers;

import com.udemy.myProject.Models.User;
import com.udemy.myProject.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceManager implements UserService {

    @Autowired
    UserRepository repository;
    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public ResponseEntity<User> save(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok().body(repository.save(user));
    }
    @Override
    public Page<User> getUsers(Pageable page, User user){
        if(user != null){
            return repository.findByUsernameNot(user.getUsername(), page);
        }
        return repository.findAll(page );

    }
}
