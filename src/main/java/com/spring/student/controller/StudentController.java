package com.spring.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.student.model.Student;
import com.spring.student.model.StudentDto;
import com.spring.student.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {


    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<StudentDto> createStudent(Student student){
        return ResponseEntity.ok(studentService.createStudent(student));
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<StudentDto>> getAllStudent(Student student){
        return ResponseEntity.ok(studentService.getAllStudent());
    }
    @DeleteMapping("/delete")
    public ResponseEntity deleteStudent(Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/edit")
    public ResponseEntity<StudentDto> editStudent(Student student){
        return ResponseEntity.ok(studentService.editStudent(student));
    }
    @GetMapping("/get")
    public ResponseEntity<StudentDto> getStudentById(Long id){
        return ResponseEntity.ok(studentService.getStudentById(id));
    }
}
