package com.uyghur.java.userauth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @Column(unique = true)
    @NotBlank( message = "Username is mandatory" )
    private String username;
    @NotBlank(message = "Password is mandatory")
    private String password;
}
