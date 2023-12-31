package com.tpe.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_student_rpt")
@Getter
@Setter
@NoArgsConstructor      // Default Constructor
@AllArgsConstructor     // Parameterized Constructor
//@RequiredArgsConstructor    // That won't let you set fields without final keywords.
public class Student {          // pojo, entity

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE) // Setter cannot be used with that field.
    private Long id;

    @NotNull(message = "First name cannot be null.")
    @NotBlank(message = "First name cannot be white space")     //  @NotNull, @NotEmpty
    @Size(min = 2, max = 25, message = "First name '${validatedValue}' cannot exceed those character limits: {min} and {max}")
    @Column(nullable = false, length = 25)
    private /*final*/ String name;

    @Column(nullable = false, length = 25)
    private /*final*/ String lastName;

    private /*final*/ Integer grade;

    @Email(message = "Provide a valid e-mail.")     // @, domain ext. (.com, .net, etc.)
    @Column(nullable = false, length = 55, unique = true)
    private /*final*/ String email;       // hello, jaceEmail       -->     xyz@yyy.com

    private /*final*/ String phoneNumber;

    @Setter(AccessLevel.NONE)
    private LocalDateTime creationDate = LocalDateTime.now();

    @OneToMany(mappedBy = "student")
    private List<Book> bookList = new ArrayList<>();

    // Constructor



    // Getter - Setter


    // toString
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", grade=" + grade +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}