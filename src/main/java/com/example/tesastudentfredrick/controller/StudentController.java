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

//    @GetMapping("/id/{studentId}")
//    public ResponseEntity<StudentResponse<Student>> getStudentById(@PathVariable Integer studentId) {
//        Student student = studentService.getStudentById(studentId);
//
//        StudentResponse<Student> response = StudentResponse.<Student>builder()
//                .studentResponseCode("00")
//                .studentResponseMessage("SUCCESS")
//                .studentResponseData(student)  // ← This becomes the JSON under `data`
//                .build();
//
//        return ResponseEntity.ok(response);
//    }

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
    public ResponseEntity<List<Student>> getStudentByFirstName(@Valid @PathVariable("studentFirstName") String studentFirstName) {
        List<Student> student = studentService.getStudentByFirstName(studentFirstName);
        return ResponseEntity.ok().body(student);
    }

    @GetMapping("/search-string/{searchString}")
    public ResponseEntity<List<Student>> getStudentBySearchString(@Valid @PathVariable("searchString") String searchString) {
        List<Student> student = studentService.getStudentBySearchString(searchString);
        return ResponseEntity.ok().body(student);
    }

    @GetMapping("/all-students")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok().body(studentService.getAllStudents());
    }

    @DeleteMapping("/delete-student{studentId}")
    public ResponseEntity<String> deleteStudentById(@Valid @PathVariable("studentId") Integer studentId) {
        studentService.deleteStudentById(studentId);
        return ResponseEntity.ok().body("Student deleted successfully");
    }
}