package com.spring.student.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.student.model.Student;
import com.spring.student.model.StudentDto;

@Service
public interface StudentService {
    
    public StudentDto createStudent(Student student);
    public List<StudentDto> getAllStudent(Student student);
    public void deleteStudent(Long id);
    public StudentDto editStudent(Student student);
    public StudentDto getStudentById(Long id);

}
