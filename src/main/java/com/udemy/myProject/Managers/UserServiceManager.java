package com.udemy.myProject.Managers;

import com.udemy.myProject.Errors.NotFoundException;
import com.udemy.myProject.File.FileService;
import com.udemy.myProject.Models.Hoax;
import com.udemy.myProject.Models.User;
import com.udemy.myProject.Repositories.UserRepository;
import com.udemy.myProject.vm.UserUpdateVM;
import com.udemy.myProject.vm.UserVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Base64;
import java.util.List;

@Service
public class UserServiceManager implements UserService {

    @Autowired
    UserRepository repository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    FileService fileService;

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

    @Override
    public User getByUsername(String username) {
        User inDB = repository.findByusername(username);
        if(inDB == null){
            throw  new NotFoundException();
        }
        return inDB;
    }

    @Override
    public User updateUser(String username, UserUpdateVM updateUser) {
        User inDB = getByUsername(username);
        inDB.setDisplayName(updateUser.getDisplayName());
        if(updateUser.getImage() != null){
           // inDB.setImage(updateUser.getImage());
            String oldImageName =inDB.getImage();
            try {
                String storedFileName = fileService.writeBase64EncodedStringToFile(updateUser.getImage());
                inDB.setImage(storedFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileService.deleteFile(oldImageName);
        }
        return repository.save(inDB);

    }




}
