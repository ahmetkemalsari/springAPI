package com.udemy.myProject.Managers;

import com.udemy.myProject.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserService {
    public ResponseEntity<?> save(User user);
    public Page<User> getUsers(Pageable page,User user);
}
