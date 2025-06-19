package com.example.tesastudentfredrick.controller;

import com.example.tesastudentfredrick.model.entity.Student;
import com.example.tesastudentfredrick.model.request.student.StudentCreateRequest;
import com.example.tesastudentfredrick.model.request.student.StudentUpdateRequest;
import com.example.tesastudentfredrick.model.response.StudentResponse;
import com.example.tesastudentfredrick.services.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {

        this.studentService = studentService;
    }

//    @PostMapping("/create-student")
//    public ResponseEntity<StudentResponse<String>> createStudent(@Valid @RequestBody StudentCreateRequest request) {
//        studentService.createStudent(request);
//
//        StudentResponse<String> response = StudentResponse.<String>builder()
//                .studentResponseCode("00")
//                .studentResponseMessage("SUCCESS")
//                .studentResponseData("Student created successfully")
//                .build();
//
//        return ResponseEntity.ok(response);
//    }

    @PostMapping("/create-student")
    public ResponseEntity<String> createStudent(@Valid @RequestBody StudentCreateRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

            Map<String, String> response = new HashMap<>();
            response.put("status", String.valueOf(400));
            response.put("errors", errors.toString());

            try {
                String json = new ObjectMapper().writeValueAsString(response);
                return ResponseEntity.badRequest().body(json);
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Error formatting validation response.");
            }
        }
        studentService.createStudent(request);
        return ResponseEntity.ok().body("Student created successfully");
    }

    @PatchMapping("/update-student")
    public ResponseEntity<String> updateStudent(@Valid @RequestBody StudentUpdateRequest request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

            Map<String, String> response = new HashMap<>();
            response.put("status", String.valueOf(400));
            response.put("errors", errors.toString());
            try {
                String json = new ObjectMapper().writeValueAsString(response);
                return ResponseEntity.badRequest().body(json);
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Error formatting validation response.");
            }
        }
        studentService.updateStudent(request);
        return ResponseEntity.ok().body("Student updated successfully");
    }

    @GetMapping("/id/{studentId}")
    public ResponseEntity<StudentResponse<?>> getStudentById(@Valid @PathVariable("studentId") Integer studentId) {
        try {
            Student student = studentService.getStudentById(studentId);

            StudentResponse<Student> response = StudentResponse.<Student>builder()
                    .studentResponseCode("00")
                    .studentResponseMessage("SUCCESS")
                    .studentResponseData(student)
                    .build();

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            StudentResponse<String> errorResponse = StudentResponse.<String>builder()
                    .studentResponseCode("22")
                    .studentResponseMessage("ERROR")
                    .studentResponseData(e.getMessage()) // or a custom message like "Student not found"
                    .build();

            return ResponseEntity.status(400).body(errorResponse); // 400 Bad Request or 404 if more appropriate
        }
    }


    @GetMapping("/first-name/{studentFirstName}")
    public ResponseEntity<StudentResponse<?>> getStudentByFirstName(@Valid @PathVariable("studentFirstName") String studentFirstName) {
        try {
            List<Student> students = studentService.getStudentByFirstName(studentFirstName);

            StudentResponse<List<Student>> response = StudentResponse.<List<Student>>builder()
                    .studentResponseCode("00")
                    .studentResponseMessage("SUCCESS")
                    .studentResponseData(students)
                    .build();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            StudentResponse<Object> errorResponse = StudentResponse.builder()
                    .studentResponseCode("22")
                    .studentResponseMessage("ERROR")
                    .studentResponseData(e.getMessage())
                    .build();
            return ResponseEntity.status(400).body(errorResponse);
        }
    }

    @GetMapping("/search-string/{searchString}")
    public ResponseEntity<StudentResponse<?>> getStudentBySearchString(@Valid @PathVariable("searchString") String searchString) {
        try {
            List<Student> students = studentService.getStudentBySearchString(searchString);

            StudentResponse<List<Student>> response = StudentResponse.<List<Student>>builder()
                    .studentResponseCode("00")
                    .studentResponseMessage("SUCCESS")
                    .studentResponseData(students)
                    .build();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            StudentResponse<Object> errorResponse = StudentResponse.builder()
                    .studentResponseCode("22")
                    .studentResponseMessage("ERROR")
                    .studentResponseData(e.getMessage())
                    .build();
            return ResponseEntity.status(400).body(errorResponse);
        }
    }

    @GetMapping("/all-students")
    public ResponseEntity<StudentResponse<?>> getAllStudents() {
        try {
            List<Student> students = studentService.getAllStudents();

            StudentResponse<List<Student>> response = StudentResponse.<List<Student>>builder()
                    .studentResponseCode("00")
                    .studentResponseMessage("SUCCESS")
                    .studentResponseData(students)
                    .build();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            StudentResponse<Object> errorResponse = StudentResponse.builder()
                    .studentResponseCode("22")
                    .studentResponseMessage("ERROR")
                    .studentResponseData(e.getMessage())
                    .build();
            return ResponseEntity.status(400).body(errorResponse);
        }
    }

    @DeleteMapping("/delete-student{studentId}")
    public ResponseEntity<String> deleteStudentById(@Valid @PathVariable("studentId") Integer studentId) {
        studentService.deleteStudentById(studentId);
        return ResponseEntity.ok().body("Student deleted successfully");
    }
}