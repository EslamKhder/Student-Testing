package com.spring.student.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.spring.student.Repo.StudentRepo;
import com.spring.student.model.Student;
import com.spring.student.model.StudentDto;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {

    @Mock
    private StudentService studentService;

    @Mock
    private StudentRepo studentRepo;

    @Test
    public void createStudent_thenValidate(){
        studentService = new StudentServiceImpl(studentRepo);
        Student student = createStudent();
        Mockito.when(studentRepo.save(student)).thenReturn(createStudentWithID());

        StudentDto studentDto = studentService.createStudent(student);
        Assertions.assertEquals(1,studentDto.getId());
        Assertions.assertEquals("Islam",studentDto.getName());
        Assertions.assertEquals(20,studentDto.getAge());
        Assertions.assertEquals("01113903660",studentDto.getPhone());
        Assertions.assertTrue(studentDto.isActive());

    }


    private Student createStudent(){
        return new Student("Islam",20,"01113903660",true);
    }
    private Student createStudentWithID(){
        return new Student(1,"Islam",20,"01113903660",true);
    }
}
