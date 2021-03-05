package com.udemy.myProject.Controller;


import com.udemy.myProject.Errors.ApiError;
import com.udemy.myProject.Managers.UserServiceManager;
import com.udemy.myProject.Models.User;
import com.udemy.myProject.Shared.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


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
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handeValidationException(MethodArgumentNotValidException exception){
        ApiError err = new ApiError(400,"Validation Error", "/api/1.0/users");
        Map<String,String> validationErrors = new HashMap<>();
        for(FieldError fieldError : exception.getBindingResult().getFieldErrors()){
            validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        err.setValidationErrors(validationErrors);
        return  err;
    }
}
