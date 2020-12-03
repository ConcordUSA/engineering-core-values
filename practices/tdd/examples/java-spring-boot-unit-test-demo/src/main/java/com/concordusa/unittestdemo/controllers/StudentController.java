package com.concordusa.unittestdemo.controllers;

import com.concordusa.unittestdemo.model.Student;
import com.concordusa.unittestdemo.model.Students;
import com.concordusa.unittestdemo.service.StudentService;

import java.net.URI;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(path="/employees", produces = "application/json")
    public Students getStudents()
    {
        Students response = new Students();
        ArrayList<Student> list = new ArrayList<>();
        studentService.findAll().forEach(list::add);
        response.setStudentList(list);
        return response;
    }

    @PostMapping(path= "/students", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> addStudent(@RequestBody Student student) {

        //add resource
        student = studentService.save(student);

        //Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(student.getStudentId())
                .toUri();

        //Send location in response
        return ResponseEntity.created(location).build();
    }
}