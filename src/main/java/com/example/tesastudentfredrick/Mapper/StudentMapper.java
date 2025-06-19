package com.example.tesastudentfredrick.Mapper;

import com.example.tesastudentfredrick.model.entity.Student;
import com.example.tesastudentfredrick.model.request.student.StudentCreateRequest;
import com.example.tesastudentfredrick.model.request.student.StudentUpdateRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student toEntity(StudentCreateRequest request);
    Student toEntity(StudentUpdateRequest request);
}