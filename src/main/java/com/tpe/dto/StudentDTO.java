package com.tpe.dto;
import com.tpe.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private Long id;

    @NotNull(message = "First name cannot be null.")
    @NotBlank(message = "First name cannot be white space")     //  @NotNull, @NotEmpty
    @Size(min = 2, max = 25, message = "First name '${validatedValue}' cannot exceed those character limits: {min} and {max}")
    private String name;

    private String lastName;

    private Integer grade;

    @Email(message = "Provide a valid e-mail.")     // @, domain ext. (.com, .net, etc.)
    private String email;       // hello, jaceEmail       -->     xyz@yyy.com

    private String phoneNumber;

    private LocalDateTime creationDate = LocalDateTime.now();

    // Student      -->     StudentDTO
    public StudentDTO(Student student){
        this.id = student.getId();
        this.name = student.getName();
        this.lastName = student.getLastName();
        this.grade = student.getGrade();
        this.email = student.getEmail();
        this.phoneNumber = student.getPhoneNumber();
    }

}