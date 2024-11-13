package com.ca.apiCA.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Message implements Serializable {
    @Id
    private String title;
    private String content;
    private String date;
    private String userId;
}
