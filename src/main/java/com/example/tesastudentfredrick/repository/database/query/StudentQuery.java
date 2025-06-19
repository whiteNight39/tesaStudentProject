package com.example.tesastudentfredrick.repository.database.query;

public class StudentQuery {

    public static final String INSERT_STUDENT = """
        INSERT INTO TESA_FREDRICK_Student (studentFirstName, studentLastName, studentStateOfOrigin, studentAge, studentStatus, studentCreatedAt, studentUpdatedAt)
        VALUES (:studentFirstName, :studentLastName, :studentStateOfOrigin, :studentAge, COALESCE(:studentStatus, 'ACTIVE'), GETDATE(), GETDATE())
    """;

    public static final String UPDATE_STUDENT ="""
        UPDATE TESA_FREDRICK_Student
        SET
          studentFirstName = COALESCE(:studentFirstName, studentFirstName),
          studentLastName = COALESCE(:studentLastName, studentLastName),
          studentStateOfOrigin = COALESCE(:studentStateOfOrigin, studentStateOfOrigin),
          studentAge = COALESCE(:studentAge, studentAge),
          studentStatus = COALESCE(:studentStatus, studentStatus),
          studentUpdatedAt = GETDATE()
        WHERE studentId = :studentId
    """;

    public static final String GET_STUDENT_BY_ID = """            
        SELECT *
        FROM TESA_FREDRICK_Student
        WHERE studentId = :studentId
        AND studentStatus != 'DELETED'
    """;

    public static final String GET_STUDENT_BY_FIRST_NAME = """
        SELECT *
        FROM TESA_FREDRICK_Student
        WHERE studentFirstName = :studentFirstName
        AND studentStatus != 'DELETED'
    """;

    public static final String GET_ALL_STUDENTS = """
        SELECT *
        FROM TESA_FREDRICK_Student
        WHERE studentStatus != 'DELETED'
    """;

    public static final String DELETE_STUDENT_BY_ID = """
        UPDATE TESA_FREDRICK_Student
        SET studentStatus = 'DELETED'
        WHERE studentId = :studentId
    """;

    public static final String SEARCH_FOR_STRING = """
    SELECT *
    FROM TESA_FREDRICK_Student
    WHERE studentFirstName  LIKE :searchString
       OR studentLastName  LIKE :searchString
       OR studentStateOfOrigin  LIKE :searchString
       AND studentStatus != 'DELETED'
    """;

}
