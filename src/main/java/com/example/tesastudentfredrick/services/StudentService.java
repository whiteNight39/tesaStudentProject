package com.example.tesastudentfredrick.services;
import com.example.tesastudentfredrick.Mapper.StudentMapper;
import com.example.tesastudentfredrick.model.entity.Student;
import com.example.tesastudentfredrick.model.request.student.StudentCreateRequest;
import com.example.tesastudentfredrick.model.request.student.StudentUpdateRequest;
import com.example.tesastudentfredrick.repository.database.interfaces.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Autowired
    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {

        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

//    public int createStudent(StudentCreateRequest request) {
//
//        Gson gson = new Gson();
//        var student = gson.fromJson(gson.toJson(request), Student.class);
//
//        System.out.println("STUDENT NAME >>");
//        System.out.println(student.getStudentFirstName());
//
//        System.out.println("STUDENT Id >>");
//        System.out.println(student.getStudentId());
//
//        return studentRepository.createStudent(student);
//    }

    public void createStudent(StudentCreateRequest request) {

        try {
            Student student = studentMapper.toEntity(request);
            student.setStudentStatus("ACTIVE");
            studentRepository.createStudent(student);
        } catch (DataAccessException e) {
            throw new RuntimeException("Unable to create student");
        }

    }

    public void updateStudent(StudentUpdateRequest request) {

        Student student = studentMapper.toEntity(request);
        studentRepository.updateStudent(student);
    }

    public Student getStudentById(Integer studentId) {
        return studentRepository.getStudentById(studentId);
    }

    public List<Student> getStudentByFirstName(String studentFirstName) {
        return studentRepository.getStudentByFirstName(studentFirstName);
    }

    public List<Student> getStudentBySearchString(String searchString) {
        return studentRepository.getStudentBySearchString(searchString);
    }

    public List<Student> getAllStudents() {
        return studentRepository.getAllStudent();
    }

    public void deleteStudentById(Integer studentId) {
        studentRepository.deleteStudentById(studentId);
    }

}