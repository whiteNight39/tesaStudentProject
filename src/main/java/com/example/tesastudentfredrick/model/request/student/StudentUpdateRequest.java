package com.example.tesastudentfredrick.model.request.student;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentUpdateRequest {

    @NotNull(message = "Student ID is required")
    private Integer studentId;

    @Schema(description = "Student's first name", example = "Jane")
    @Pattern(regexp = ".*\\S.*", message = "First name cannot be blank")
    private String studentFirstName;

    @Schema(description = "Student's last name", example = "Doe")
    @Pattern(regexp = ".*\\S.*", message = "Last name cannot be blank")
    private String studentLastName;

    @Schema(description = "Student's state of origin", example = "Lagos")
    @Pattern(regexp = ".*\\S.*", message = "State of origin cannot be blank")
    private String studentStateOfOrigin;

    @Min(value = 1, message = "Age must be at least 1")
    private Integer studentAge;
}
