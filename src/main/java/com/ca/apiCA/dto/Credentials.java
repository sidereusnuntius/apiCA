package com.ca.apiCA.dto;

import java.io.Serializable;
public record Credentials(
    String username,
    String password
) implements Serializable { }
