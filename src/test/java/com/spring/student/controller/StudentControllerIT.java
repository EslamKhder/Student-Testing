package com.spring.student.controller;

import jdk.net.SocketFlow;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.spring.student.StudentApplicationIT;
import com.spring.student.model.Student;
import com.spring.student.util.RequestUtil;

public class StudentControllerIT extends StudentApplicationIT {

    @Autowired
    private RequestUtil requestUtil;

    @Test
    public void createStudent_thenValidate(){
        Student student = new Student();
        student.setName("Eslam");
        student.setAge(22);
        student.setPhone("01113903660");
        student.setActive(true);
        ResponseEntity<Object> responseEntity =
                requestUtil.post("/api/create",student,null,Object.class);
        Assertions.assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
    }
    @Test
    public void getAllStudent_thenValidate(){
        ResponseEntity<Object> responseEntity =
                requestUtil.get("/api/getAll",null,null,Object.class);
        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }
}
