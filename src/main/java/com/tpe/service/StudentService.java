package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

        Student student = repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Couldn't find the student with id: "+id));

        return student;

    }

    public void deleteById (Long id) {
        if (!repository.existsById(id)){
            throw new ResourceNotFoundException("Couldn't find the student with id: "+id);
        } repository.deleteById(id);
    }
}