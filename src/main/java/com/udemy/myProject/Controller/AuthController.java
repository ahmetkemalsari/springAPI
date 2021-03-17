package com.udemy.myProject.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.udemy.myProject.Models.User;
import com.udemy.myProject.Shared.CurrentUser;
import com.udemy.myProject.vm.UserVM;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/1.0")
public class AuthController {


    @RequestMapping(method = RequestMethod.POST,value = "/auth")
    UserVM createUser(@CurrentUser User user){
        return new UserVM(user);
    }
}
 