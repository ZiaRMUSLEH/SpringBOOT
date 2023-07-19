package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // Using the Restful API
@RequestMapping("/students")                // http://localhost:8080/students
public class StudentController {

    @Autowired
    private StudentService service;

    // Get All Students
    @GetMapping     // http://localhost:8080/students + GET
    public ResponseEntity<List<Student>> getAll(){
        List<Student> students = service.getAllStudents();
        return ResponseEntity.ok(students);     // return Students & HttpStatus: 200 (OK)
        //return new ResponseEntity<>(students, HttpStatus.OK);
    }

    // Save a Student
    @PostMapping        // http://localhost:8080/students + POST
    public ResponseEntity<Map<String, String>> createStudent(@Valid @RequestBody Student student){

        service.saveStudent(student);

        Map<String, String> map = new HashMap<>();
        map.put("message", "Student has been saved successfully.");
        map.put("success", "true");

        return new ResponseEntity<>(map, HttpStatus.CREATED);       // HttpStatus: 201 (CREATED)

    }

    // Get a Student with their ID
    @GetMapping("/query")               // http://localhost:8080/students/query?id=1
    public ResponseEntity<Student> getStudentWithRequestParam(@RequestParam("id") Long id){

        Student student = service.getStudentById(id);

        return new ResponseEntity<>(student, HttpStatus.OK);        // ResponseEntity.ok(student);

    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<String> deleteById(@RequestParam ("id")Long id ){
        service.deleteById(id);

        return ResponseEntity.ok("Student deleted Successfully");
    }
    /*
    JSON EXAMPLE:

            {
                "name": "Jace",
                "grade": 90,
            }

     */



}