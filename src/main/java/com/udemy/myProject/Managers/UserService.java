package com.udemy.myProject.Managers;

import com.udemy.myProject.Models.Hoax;
import com.udemy.myProject.Models.User;
import com.udemy.myProject.vm.UserUpdateVM;
import com.udemy.myProject.vm.UserVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    public ResponseEntity<?> save(User user);
    public Page<User> getUsers(Pageable page,User user);
    public User getByUsername(String username);
    public User updateUser(String username, UserUpdateVM updateUser);

}
