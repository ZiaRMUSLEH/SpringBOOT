package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public List<Student> getAllStudents() {

        return repository.findAll();

    }


    public void saveStudent(Student student) {
        if (repository.existsByEmail(student.getEmail())){
            throw new ConflictException("Student with that email already exists: "+student.getEmail());
        }

        repository.save(student);

    }

    public Student getStudentById(Long id) {

        Student student = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Couldn't find the student with id: "+id));

        return student;

    }

    public void deleteStudent(Long id) {

        Student student = getStudentById(id);

        repository.delete(student);

    }

    public void updateStudent(Long id, StudentDTO studentDTO) {

        /*
            DATA THAT I ALREADY HAVE:       --> student.getEmail()
                {
                    "name":"Jace",
                    "email": "abc@xyz.com"
                }

            DATA I WANT TO ADD NOW:         --> studentDTO.getEmail()
                {
                    "name":"JaceB",
                    "email": "abc@xyz.com"
                }

         */

        Student existingStudent = getStudentById(id);       // Student from my Database

        boolean emailExists = repository.existsByEmail(studentDTO.getEmail());

        boolean emailBelongsToTheSameStudent = studentDTO.getEmail().equals(existingStudent.getEmail());

        if (emailExists && !emailBelongsToTheSameStudent){
            throw new ConflictException("Student with that email already exists: "+studentDTO.getEmail());
        }

        existingStudent.setName(studentDTO.getName());
        existingStudent.setLastName(studentDTO.getLastName());
        existingStudent.setGrade(studentDTO.getGrade());
        existingStudent.setEmail(studentDTO.getEmail());
        existingStudent.setPhoneNumber(studentDTO.getPhoneNumber());

        repository.save(existingStudent);   // saveOrUpdate()
    }

    public Page<Student> getAllStudentsWithPage(Pageable pageable) {

        return repository.findAll(pageable);

    }
}