package com.udemy.myProject.Managers;

import com.udemy.myProject.Models.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    public ResponseEntity<?> save(User user);
}
