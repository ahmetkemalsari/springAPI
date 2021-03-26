package com.udemy.myProject.Models;


import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
public class Hoax {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 1000 )
    @Size(min = 1, max = 1000)
    private String content;

    private Date timestamp;

    @ManyToOne
    private User user;

}
