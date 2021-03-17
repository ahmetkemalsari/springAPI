package com.udemy.myProject.vm;

import com.udemy.myProject.Models.User;
import lombok.Data;

@Data
public class UserVM {
    public UserVM(User user) {
        this.username = user.getUsername();
        this.displayName = user.getDisplayName();
        this.image = user.getImage();
    }

    private String username;
    private String displayName;
    private String image;

}
