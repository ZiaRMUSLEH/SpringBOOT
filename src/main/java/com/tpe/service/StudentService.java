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

    public void deleteById (Long id) {
        if(!repository.existsById(id)){
            throw  new ResourceNotFoundException("couldn't find the student by this id: "+id);
        }
        repository.deleteById(id);
    }






    public void updateById (StudentDTO studentDTO, Long id) {

        Student existingStudent = getStudentById(id);

        boolean isEmailExists = repository.existsByEmail(studentDTO.getEmail());

        boolean emailBelongsToTheSameStudent = studentDTO.getEmail().equals(existingStudent.getEmail());

        if(isEmailExists && !emailBelongsToTheSameStudent){
            throw new ConflictException("Student with that email already exists: "+studentDTO.getEmail());
        }
        existingStudent.setName(studentDTO.getName());
        existingStudent.setLastName(studentDTO.getLastName());
        existingStudent.setGrade(studentDTO.getGrade());
        existingStudent.setEmail(studentDTO.getEmail());
        existingStudent.setPhoneNumber(studentDTO.getPhoneNumber());

        repository.save(existingStudent);

    }

    public Page<Student> getAllStudentsWithPage (Pageable pageable) {
       return repository.findAll(pageable);
    }

    public List<Student> getStudentsByLastName(String lastName) {

        return repository.findAllByLastName(lastName);

    }

    public List<Student> getStudentsByGrade(Integer grade) {

        return repository.findStudentsByGradeWithJPQL(grade);
        //return repository.findStudentsByGradeWithSQL(grade);

    }

    public StudentDTO getStudentDTOById(Long id) {

        return repository.findStudentDTOById(id).orElseThrow(()-> new ResourceNotFoundException("Couldn't find the student with specified ID: "+id));

    }
}