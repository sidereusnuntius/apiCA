package com.ca.apiCA.controller;

import com.ca.apiCA.exception.ExceptionDTO;
import com.ca.apiCA.model.User;
import com.ca.apiCA.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(
            summary = "Cria novo usuário.",
            method = "POST",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content =
            @Content(schema = @Schema(implementation = User.class))),
            responses = {
                    @ApiResponse(
                            description = "Usuário criado.",
                            responseCode = "201",
                            content = { @Content}),
                    @ApiResponse(
                            description = "Um ou mais campos está vazio ou possui valor inválido..",
                            responseCode = "422",
                    content = { @Content(schema = @Schema(implementation = ExceptionDTO.class))})
            }
    )
    @PostMapping(path = "/cadastro")
    public ResponseEntity<String> insert(@RequestBody @Validated User user) {
        userService.insert(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
