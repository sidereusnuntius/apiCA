package com.ca.apiCA.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(title = "Nome de usuário", example = "xanx")
    private String username;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "E-mail em branco.")
    @Schema(title = "Endereço de e-mail", example = "xande1234@gmail.com")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Senha em branco.")
    @Schema(title = "Senha", example = "xanxanx")
    private String password;
    @Schema(title = "Data de nascimento", example = "2024-12-03")
    private Date birth;

    private String role;
}
