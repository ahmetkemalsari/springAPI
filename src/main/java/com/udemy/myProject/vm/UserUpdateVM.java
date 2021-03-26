package com.udemy.myProject.vm;

import com.udemy.myProject.Shared.FileType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserUpdateVM {

    @NotNull(message = "{hoaxify.constraints.displayName.NotNull.message}")
    @Size(min = 4,max = 255)
    private  String  displayName;
    @FileType(types = {"jpeg","png"})
    private  String image;

}
