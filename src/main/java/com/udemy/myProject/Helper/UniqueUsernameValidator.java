package com.udemy.myProject.Helper;

import com.udemy.myProject.Models.User;
import com.udemy.myProject.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername,String> {
    @Autowired
    UserRepository repository;
    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        User user = repository.findByusername(username);
        if(user != null){
            return false;
        }
        return true;
    }
}
