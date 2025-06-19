package com.example.tesastudentfredrick.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse<T> {

    private String studentResponseCode;
    private String studentResponseMessage;
    private T studentResponseData;
}