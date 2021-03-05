package com.udemy.myProject.Models;



import com.fasterxml.jackson.annotation.JsonView;
import com.udemy.myProject.Helper.UniqueUsername;
import com.udemy.myProject.Helper.Views;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "{hoaxify.constraints.userName.NotNull.message}")
    @UniqueUsername
    @Size(min = 4,max = 255)
    @JsonView(Views.Base.class)
    private String username;

    @NotNull(message = "{hoaxify.constraints.displayName.NotNull.message}")
    @Size(min = 4,max = 255)
    @JsonView(Views.Base.class)
    private String displayName;

    @NotNull(message = "{hoaxify.constraints.password.NotNull.message}")
    @Size(min = 8,max = 255)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "{hoaxify.constraints.password.Pattern.message}")
    private String password;

    @JsonView(Views.Base.class)
    private String image;

}
