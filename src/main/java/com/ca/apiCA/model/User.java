package com.ca.apiCA.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "user_table")
@Data
public class User implements Serializable {
    @Id
    @NotBlank(message = "Nome de usuário em branco.")
    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    @NotBlank(message = "E-mail em branco.")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Senha em branco.")
    private String password;
    private Date birth;

    private String role;
}
