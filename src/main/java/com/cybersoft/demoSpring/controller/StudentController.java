package com.cybersoft.demoSpring.controller;

import com.cybersoft.demoSpring.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("/student")
public class StudentController {
    private final List<Student> lists = new ArrayList<>();
    @PostMapping("/add-student/{name}/{age}")
    public ResponseEntity<List<Student>> addStudentByPathVariable(
            @PathVariable("name") String name,
            @PathVariable("age") int age
    ) {
        Student st = new Student();
        st.setAge(age);
        st.setName(name);
        lists.add(st);
        return new ResponseEntity<>(lists, HttpStatus.OK);
    }
    @PostMapping("/add-student-2/")
    public ResponseEntity<List<Student>> addStudentByRequestParam(
            @RequestParam("name") String name,
            @RequestParam("age") int age
    ) {
        Student st = new Student();
        st.setAge(age);
        st.setName(name);
        lists.add(st);
        return new ResponseEntity<>(lists, HttpStatus.OK);
    }
    @PostMapping("/add-student-3/")
    public ResponseEntity<List<Student>> addStudentByRequestBody(
           @RequestBody Student student
    ) {
        Student st = new Student();
        st.setAge(student.getAge());
        st.setName(student.getName());
        lists.add(st);
        return new ResponseEntity<>(lists, HttpStatus.OK);
    }
}
