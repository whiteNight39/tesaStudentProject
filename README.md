# tesaStudentProject
## Fredrick 🖤 | Tesa Student Management API Documentation  

A Spring Boot application built to manage student records, demonstrating CRUD operations, validation, and clean architecture for backend development in Java.

## Features

- Create a new student
- Update existing student records
- Retrieve student details by ID
- Search students by first name or multiple fields
- View all students
- Delete student by ID
- Standardized response format with success and error codes
- Integrated validation using `jakarta.validation`

## Endpoints

| Method | Endpoint                          | Description                     |
|--------|-----------------------------------|---------------------------------|
| POST   | `/student/create-student`         | Create a new student            |
| PATCH  | `/student/update-student`         | Update student details          |
| GET    | `/student/id/{studentId}`         | Get student by ID               |
| GET    | `/student/first-name/{firstName}` | Get students by first name      |
| GET    | `/student/all-students`           | Retrieve all students           |
| GET    | `/student/search/{searchString}`  | Search by name, state, etc.     |
| DELETE | `/student/delete-student/{id}`    | Delete student by ID            |

## Response Structure

All responses follow the format:

```json
{
  "studentResponseCode": "00",
  "studentResponseMessage": "SUCCESS",
  "studentResponseData": { /* Payload */ }
}
