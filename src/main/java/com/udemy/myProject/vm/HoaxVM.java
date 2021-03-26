package com.udemy.myProject.vm;

import com.udemy.myProject.Models.Hoax;
import com.udemy.myProject.Models.User;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class HoaxVM {


    private long id;

    private String content;

    private long timestamp;

    private UserVM user;

    public HoaxVM(Hoax hoax){
        this.setId(hoax.getId());
        this.setContent(hoax.getContent());
        this.setTimestamp(hoax.getTimestamp().getTime());
        this.setUser(new UserVM(hoax.getUser()));
    }
}
