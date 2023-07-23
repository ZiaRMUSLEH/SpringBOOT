package com.tpe.repository;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository     // Optional
public interface StudentRepository extends JpaRepository<Student, Long> {       // Extending --> Needed.
    boolean existsByEmail(String email);

    List<Student> findAllByLastName(String lastName);
    //List<Student> findByLastName(String lastName);

    // JPQL
    @Query("SELECT s FROM Student s WHERE s.grade =:pGrade")
    List<Student> findStudentsByGradeWithJPQL(@Param("pGrade") Integer grade);

    //SQL
    @Query(value = "SELECT * FROM t_student s WHERE s.grade =:pGrade", nativeQuery = true)
    List<Student> findStudentsByGradeWithSQL(@Param("pGrade") Integer grade);


    // Student to StudentDTO, JPQL
    @Query("SELECT new com.tpe.dto.StudentDTO(s) FROM Student s WHERE s.id =:id")
    Optional<StudentDTO> findStudentDTOById(@Param("id") Long id);





    /*

            findAll()
            findById()
            existsById()
            ...
            ...

            findByEmail(email)   --> Email.
            existsByEmail(email) --> Email.
            existsByName(name)  --> Name.

     */


}