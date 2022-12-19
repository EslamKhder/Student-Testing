package com.spring.student.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.student.model.Student;
import com.spring.student.model.StudentDto;

@Service
public class StudentServiceImpl implements StudentService{
    @Override
    public StudentDto createStudent(Student student) {
        return null;
    }

    @Override
    public List<StudentDto> getAllStudent(Student student) {
        return null;
    }

    @Override
    public void deleteStudent(Long id) {

    }

    @Override
    public StudentDto editStudent(Student student) {
        return null;
    }

    @Override
    public StudentDto getStudentById(Long id) {
        return null;
    }
}
