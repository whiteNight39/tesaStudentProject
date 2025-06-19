package com.example.tesastudentfredrick.model.request.student;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GetStudentByFirstName {

    private Integer studentId;
    private String studentFirstName;
    private String studentLastName;
    private String studentStateOfOrigin;
    private Integer studentAge;
    private String studentStatus;
    private LocalDateTime studentCreatedAt;
    private LocalDateTime studentUpdatedAt;
}
