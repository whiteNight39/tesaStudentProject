package com.example.tesastudentfredrick.model.request.student;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentCreateRequest {

    @NotBlank(message = "First name is required")
    private String studentFirstName;

    @NotBlank(message = "Last name is required")
    private String studentLastName;

    @NotBlank(message = "State of origin is required")
    private String studentStateOfOrigin;

    @NotNull(message = "Age is required")
    @Min(value = 1, message = "Age must be at least 1")
    private Integer studentAge;
}

