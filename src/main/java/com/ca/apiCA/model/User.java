package com.ca.apiCA.model;

import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;

@Document(collection = "user")
@Data
public class User implements Serializable {
    @Id
    @NotBlank(message = "Nome de usuário em branco.")
    @Schema(title = "Nome de usuário", example = "xanx")
    private String username;

    @NotBlank(message = "E-mail em branco.")
    @Schema(title = "Endereço de e-mail", example = "xande1234@gmail.com")
    private String email;

    @NotBlank(message = "Senha em branco.")
    @Length(min = 8, message = "Senha curta demais.")
    @Schema(title = "Senha", example = "xanxanx", description = "Deve ter mais de 8 caracteres.")
    private String password;
    @Schema(title = "Data de nascimento", example = "2024-12-03", defaultValue = "2000-01-01")
    private Date birthdate;
    private String role;

    @DBRef
    private List<Message> posts;
}
