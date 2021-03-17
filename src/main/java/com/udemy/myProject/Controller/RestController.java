package com.udemy.myProject.Controller;


import com.udemy.myProject.Managers.UserServiceManager;
import com.udemy.myProject.Models.User;
import com.udemy.myProject.Shared.CurrentUser;
import com.udemy.myProject.Shared.GenericResponse;
import com.udemy.myProject.vm.UserVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/1.0")
public class RestController {
    @Autowired
    UserServiceManager manager;



    @GetMapping("/test")
    public ResponseEntity<?> test(){
        return ResponseEntity.ok().body("çalıştırıldı");
    }


    @RequestMapping(method = RequestMethod.POST,value = "/users")
    public GenericResponse createUser(@Valid @RequestBody User user){
        manager.save(user);
        return new GenericResponse("user created");
    }


    @GetMapping("/users")
    public Page<UserVM> getUsers(Pageable page, @CurrentUser User user){
       return  manager.getUsers(page,user).map(UserVM::new);
    }

}
