package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import com.tpe.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // Using the Restful API
@RequestMapping("/students")                // http://localhost:8080/students
public class StudentController {

    Logger logger = LoggerFactory.getLogger(StudentController.class);

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

    // https://google.com/search?q=ABC      -->     RequestParam
    // https://instagram.com/profile/ABC      -->     PathVariable

    // Get a Student with their ID
    @GetMapping("/{id}")        // http://localhost:8080/students/1     + GET
    public ResponseEntity<Student> getStudentWithPathVariable(@PathVariable("id") Long id){

        Student student = service.getStudentById(id);

        return ResponseEntity.ok(student);
        //return ResponseEntity.ok(service.getStudentById(id));

    }

    // Delete Student Using Their ID
    @DeleteMapping("/{id}")     // http://localhost:8080/students/1     + DELETE
    public ResponseEntity<Map<String, String>> deleteStudentById(@PathVariable Long id){

        service.deleteStudent(id);

        Map<String, String> map = new HashMap<>();
        map.put("message", "Student has been deleted successfully.");
        map.put("success", "true");

        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    // Update a Student Using Their ID
    @PutMapping("/{id}")        // http://localhost:8080/students/1
    public ResponseEntity<Map<String, String>> updateStudent(@Valid @PathVariable("id") Long id, @RequestBody StudentDTO studentDTO){

        service.updateStudent(id, studentDTO);

        Map<String,String> map = new HashMap<>();
        map.put("message", "Student has been updated successfully.");
        map.put("success", "true");

        return ResponseEntity.ok(map);

    }

    // Get All Students With Pageable
    @GetMapping("/page")        // http://localhost:8080/students/page?page=1&size=2&sort=name&direction=ASC
    public ResponseEntity<Page<Student>> getAllStudentsWithPage(@RequestParam("page") int page,
                                                                @RequestParam("size") int size,
                                                                @RequestParam("sort") String prop,      // sort
                                                                @RequestParam("direction")Sort.Direction direction
    ){

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, prop));

        Page<Student> pageOfStudents = service.getAllStudentsWithPage(pageable);

        return ResponseEntity.ok(pageOfStudents);

    }


    /*
    JSON EXAMPLE:

            {
                "name": "Jace",
                "lastName": "Abc",
                "grade": 90,
                "email": "abc@xyz.com
            }

     */


    // Get The Students By Their Last Name
    @GetMapping("/queryLastName")       // http://localhost:8080/students/queryLastName?lastName=Abc
    public ResponseEntity<List<Student>> getStudentByLastName(@RequestParam("lastName") String lastName){

        List<Student> students = service.getStudentsByLastName(lastName);

        return ResponseEntity.ok(students);

    }

    // Get the Student By Their Grade (JPQL ->  Java Persistence Query Language)
    @GetMapping("/grade/{grade}")       // http://localhost:8080/students/grade/90
    public ResponseEntity<List<Student>> getStudentsByGrade(@PathVariable("grade") Integer grade){

        List<Student> students = service.getStudentsByGrade(grade);

        return ResponseEntity.ok(students);

    }

    // Get a StudentDTO By ID, Do mapping inside the Repo
    @GetMapping("/query/dto")       // http://localhost:8080/students/query/dto?
    public ResponseEntity<StudentDTO> getStudentDTO(@RequestParam("id") Long id){

        StudentDTO studentDTO = service.getStudentDTOById(id);

        return ResponseEntity.ok(studentDTO);

    }

    // Logging
    @GetMapping("/welcome") // http://localhost:8080/students/welcome
    public String welcome(HttpServletRequest request){

        logger.warn("--Welcome {}", request.getServletPath());

        return "Welcome to the Student Controller";
    }








}