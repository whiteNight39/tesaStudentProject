package com.example.tesastudentfredrick.model.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Student {

    private int studentId;
    private String studentFirstName;
    private String studentLastName;
    private String studentStateOfOrigin;
    private Integer studentAge;
    private String studentStatus;
    private LocalDateTime studentCreatedAt;
    private LocalDateTime studentUpdatedAt;
}
