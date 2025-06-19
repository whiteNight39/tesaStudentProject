package com.example.tesastudentfredrick.repository.database.interfaces;

import com.example.tesastudentfredrick.model.entity.Student;

import java.util.List;

public interface StudentRepository {

    int createStudent(Student student);
    int updateStudent(Student student);
    Student getStudentById(Integer studentId);
    List<Student> getStudentByFirstName(String studentFirstName);
    List<Student> getStudentBySearchString(String searchString);
    List<Student> getAllStudent();
    int deleteStudentById(Integer studentId);


//    boolean existsStudentById(Integer studentId);
}
