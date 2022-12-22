package com.spring.student.controller;

import jdk.net.SocketFlow;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.student.StudentApplicationIT;
import com.spring.student.model.Student;
import com.spring.student.model.StudentDto;
import com.spring.student.util.RequestUtil;

public class StudentControllerIT extends StudentApplicationIT {

    @Autowired
    private RequestUtil requestUtil;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createStudent_thenValidate() throws JsonProcessingException {
        Student student = new Student();
        student.setName("Eslam");
        student.setAge(22);
        student.setPhone("01113903660");
        student.setActive(true);
        ResponseEntity<String> responseEntity =
                requestUtil.post("/api/create",student,null,String.class);
        Assertions.assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());

        StudentDto studentDto = objectMapper.readValue(responseEntity.getBody(),StudentDto.class);
        Assertions.assertEquals("Eslam",studentDto.getName());
        Assertions.assertEquals(22,studentDto.getAge());
        Assertions.assertEquals("01113903660",studentDto.getPhone());
        Assertions.assertTrue(studentDto.isActive());
    }

    @Test
    public void getAllStudent_thenValidate(){
        ResponseEntity<Object> responseEntity =
                requestUtil.get("/api/getAll",null,null,Object.class);
        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    @Test
    public void deleteStudent_thenValidate(){
        ResponseEntity<Object> responseEntity =
                requestUtil.delete("/api/delete/2",null,null,Object.class);
        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    @Test
    public void editStudent_thenValidate(){
        Student student = new Student();
        student.setId(1);
        student.setName("Karim");
        student.setAge(21);
        student.setPhone("0122588885");
        student.setActive(false);
        ResponseEntity<Object> responseEntity =
                requestUtil.put("/api/edit",student,null,Object.class);
        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    @Test
    public void getStudentById_thenValidate(){
        ResponseEntity<Object> responseEntity =
                requestUtil.get("/api/get/1",null,null,Object.class);
        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }
}
