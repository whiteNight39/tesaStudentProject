package com.example.tesastudentfredrick.repository.database.implementation;

import com.example.tesastudentfredrick.model.entity.Student;
import com.example.tesastudentfredrick.repository.database.interfaces.StudentRepository;
import com.example.tesastudentfredrick.repository.database.query.StudentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public StudentRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createStudent(Student student) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("studentFirstName", student.getStudentFirstName())
                .addValue("studentLastName", student.getStudentLastName())
                .addValue("studentStateOfOrigin", student.getStudentStateOfOrigin())
                .addValue("studentAge", student.getStudentAge())
                .addValue("studentStatus", student.getStudentStatus());

        return jdbcTemplate.update(StudentQuery.INSERT_STUDENT, params);
    }

    @Override
    public int updateStudent(Student student) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("studentFirstName", student.getStudentFirstName())
                .addValue("studentLastName", student.getStudentLastName())
                .addValue("studentStateOfOrigin", student.getStudentStateOfOrigin())
                .addValue("studentAge", student.getStudentAge())
                .addValue("studentStatus", student.getStudentStatus())
                .addValue("studentId", student.getStudentId());

        return jdbcTemplate.update(StudentQuery.UPDATE_STUDENT, params);
    }

    @Override
    public Student getStudentById(Integer studentId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("studentId", studentId);

        return jdbcTemplate.queryForObject(
                StudentQuery.GET_STUDENT_BY_ID, params,
                (rs, rowNum) -> Student.builder()
                        .studentId(rs.getInt("studentId"))
                        .studentFirstName(rs.getString("studentFirstName"))
                        .studentLastName(rs.getString("studentLastName"))
                        .studentStateOfOrigin(rs.getString("studentStateOfOrigin"))
                        .studentAge(rs.getInt("studentAge"))
                        .studentStatus(rs.getString("studentStatus"))
                        .studentCreatedAt(rs.getTimestamp("studentCreatedAt").toLocalDateTime())
                        .studentUpdatedAt(rs.getTimestamp("studentUpdatedAt").toLocalDateTime())
                        .build());
    }

    @Override
    public List<Student> getStudentByFirstName(String studentFirstName) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("studentFirstName", studentFirstName);

        return jdbcTemplate.query(
                StudentQuery.GET_STUDENT_BY_FIRST_NAME, params,
                (rs, rowNum) -> Student.builder()
                        .studentId(rs.getInt("studentId"))
                        .studentFirstName(rs.getString("studentFirstName"))
                        .studentLastName(rs.getString("studentLastName"))
                        .studentAge(rs.getInt("studentAge"))
                        .studentStateOfOrigin(rs.getString("studentStateOfOrigin"))
                        .studentStatus(rs.getString("studentStatus"))
                        .studentCreatedAt(rs.getTimestamp("studentCreatedAt").toLocalDateTime())
                        .studentUpdatedAt(rs.getTimestamp("studentUpdatedAt").toLocalDateTime())
                        .build());
    }

    @Override
    public List<Student> getStudentBySearchString(String searchString) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("searchString", "%"+searchString+"%");


        return jdbcTemplate.query(
                StudentQuery.SEARCH_FOR_STRING, params,
                (rs, rowNum) -> Student.builder()
                        .studentId(rs.getInt("studentId"))
                        .studentFirstName(rs.getString("studentFirstName"))
                        .studentLastName(rs.getString("studentLastName"))
                        .studentAge(rs.getInt("studentAge"))
                        .studentStateOfOrigin(rs.getString("studentStateOfOrigin"))
                        .studentStatus(rs.getString("studentStatus"))
                        .studentCreatedAt(rs.getTimestamp("studentCreatedAt").toLocalDateTime())
                        .studentUpdatedAt(rs.getTimestamp("studentUpdatedAt").toLocalDateTime())
                        .build());
    }

    @Override
    public List<Student> getAllStudent() {

        return jdbcTemplate.query(
                StudentQuery.GET_ALL_STUDENTS,
                (rs, rowNum) -> Student.builder()
                        .studentId(rs.getInt("studentId"))
                        .studentFirstName(rs.getString("studentFirstName"))
                        .studentLastName(rs.getString("studentLastName"))
                        .studentAge(rs.getInt("studentAge"))
                        .studentStateOfOrigin(rs.getString("studentStateOfOrigin"))
                        .studentStatus(rs.getString("studentStatus"))
                        .studentCreatedAt(rs.getTimestamp("studentCreatedAt").toLocalDateTime())
                        .studentUpdatedAt(rs.getTimestamp("studentUpdatedAt").toLocalDateTime())
                        .build());
    }

    @Override
    public int deleteStudentById(Integer studentId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("studentId", studentId);

        return jdbcTemplate.update(StudentQuery.DELETE_STUDENT_BY_ID, params);
    }

//    @Override
//    public boolean existsStudentById(Integer studentId) {
//        String sql = "SELECT COUNT(*) FROM STUDENT WHERE studentId = :studentId";
//        MapSqlParameterSource params = new MapSqlParameterSource()
//                .addValue("studentId", studentId);
//        Integer count = jdbcTemplate.queryForObject(sql, params, Integer.class);
//        return count != null && count > 0;
//
//    }
}
