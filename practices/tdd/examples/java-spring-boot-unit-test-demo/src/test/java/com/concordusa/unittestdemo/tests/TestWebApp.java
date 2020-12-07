package com.concordusa.unittestdemo.tests;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import com.concordusa.unittestdemo.controllers.StudentController;
import com.concordusa.unittestdemo.model.Student;
import com.concordusa.unittestdemo.model.Students;
import com.concordusa.unittestdemo.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;


public class TestWebApp {

    @InjectMocks
    StudentController studentController;

    @Mock
    StudentService studentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveStudent()
    {
        //arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Student student = new Student();
        student.setStudentId("1");

        when(studentService.save(any(Student.class))).thenReturn(student);

        Student studentToAdd = new Student("1", "Goldy", "Computer Science", 2024);

        //act
        ResponseEntity<Void> responseEntity = studentController.addStudent(studentToAdd);

        //assert
        assertEquals(responseEntity.getStatusCodeValue(), 201);
        assertEquals(responseEntity.getHeaders().getLocation().getPath(), "/1");
    }

    @Test
    public void testFindAll()
    {
        // arrange
        Student student1 = new Student("1", "Goldy", "Computer Science", 2024);
        Student student2 = new Student("2", "Bob", "History", 2022);
        List<Student> list = new ArrayList<Student>();
        list.addAll(Arrays.asList(student1, student2));

        when(studentService.findAll()).thenReturn(list);

        // act
        Students result = studentController.getStudents();

        // assert
        assertEquals(result.getStudentList().size(), 2);
        assertEquals(result.getStudentList().get(0).getName(),student1.getName());
        assertEquals(result.getStudentList().get(1).getStudentId(), student2.getStudentId());
    }
}