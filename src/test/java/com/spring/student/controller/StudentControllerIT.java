package com.spring.student.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.student.Repo.StudentRepo;
import com.spring.student.StudentTestingApplicationTests;
import com.spring.student.model.Student;
import com.spring.student.model.StudentDto;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.HttpStatus.OK;

public class StudentControllerIT extends StudentTestingApplicationTests {

    private RequestUtil requestUtil;
    private DataUtil dataUtil;
    private StudentRepo studentRepo;
    private final ObjectMapper objectMapper;

    @Autowired
    public StudentControllerIT(RequestUtil requestUtil, DataUtil dataUtil, StudentRepo studentRepo, ObjectMapper objectMapper) {
        this.requestUtil = requestUtil;
        this.dataUtil = dataUtil;
        this.studentRepo = studentRepo;
        this.objectMapper = objectMapper;
    }

    @Test
    public void createStudent_thenValidate() throws JsonProcessingException {
        Student student = new Student("Hoda",20,"01113903660",true);
        ResponseEntity<String> responseEntity = requestUtil.put(
                "/student/edit", student, null,
                String.class);

        // Then - validate applicant status is ready to hire
        assertEquals(OK, responseEntity.getStatusCode());

        StudentDto studentDto = objectMapper
                .readValue(responseEntity.getBody(), StudentDto.class);

        assertEquals("Hoda", studentDto.getName());
        assertEquals("01113903660", studentDto.getPhone());
        assertTrue(studentDto.isActive());
    }

    @Test
    public void getAllStudent_thenValidate() throws JsonProcessingException {
        final ResponseEntity<String> getWorkingMethods =
                requestUtil.get("/student/getAll",
                        null, null, String.class);
        assertEquals(OK, getWorkingMethods.getStatusCode());
        StudentDto[] studentDto = objectMapper
                .readValue(getWorkingMethods.getBody(), StudentDto[].class);

        assertEquals(7,studentDto.length);
        assertEquals(1, studentDto[0].getId());
        assertEquals("Islam", studentDto[0].getName());
        assertEquals("01113903660", studentDto[0].getPhone());
        assertTrue(studentDto[0].isActive());
    }

    @Test
    public void deleteStudent_thenValidate() throws JsonProcessingException {
        final ResponseEntity<Object> response = requestUtil.delete(
                format("/student/delete/12"),
                null, null, Object.class);

        // Then - validate
        assertEquals(OK, response.getStatusCode());
    }

    @Test
    public void editStudent_thenValidate() throws JsonProcessingException {
        Student student = new Student(1,"Islam",20,"01113903660",true);
        ResponseEntity<String> responseEntity = requestUtil.put(
                "/student/edit", student, null,
                String.class);

        // Then - validate applicant status is ready to hire
        assertEquals(OK, responseEntity.getStatusCode());

        StudentDto studentDto = objectMapper
                .readValue(responseEntity.getBody(), StudentDto.class);

        assertEquals(1, studentDto.getId());
        assertEquals("Islam", studentDto.getName());
        assertEquals("01113903660", studentDto.getPhone());
        assertTrue(studentDto.isActive());
    }
    @Test
    public void getStudentById_thenValidate() throws JsonProcessingException {
        final ResponseEntity<String> responseEntity =
                requestUtil.get("/student/get/1",
                        null, null, String.class);
        assertEquals(OK, responseEntity.getStatusCode());
        StudentDto studentDto = objectMapper
                .readValue(responseEntity.getBody(), StudentDto.class);

        assertEquals(1, studentDto.getId());
        assertEquals("Islam", studentDto.getName());
        assertEquals("01113903660", studentDto.getPhone());
        assertTrue(studentDto.isActive());
    }
}
