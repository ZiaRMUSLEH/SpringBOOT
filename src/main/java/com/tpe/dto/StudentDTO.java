package com.tpe.dto;

import lombok.*;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor      // Default Constructor
@AllArgsConstructor
public class StudentDTO {


    private Long id;

    @NotNull(message = "First name cannot be null.")
    @NotBlank(message = "First name cannot be white space")     // Jace, J
    @Size(min = 2, max = 25, message = "First name '${validatedValue}' cannot exceed those character limits: {min} and {max}")
    private  String name;

    private  String lastName;

    private  Integer grade;

    @Email(message = "Provide a valid e-mail.")     // @, domain ext. (.com, .net, etc.)

    private  String email;       // hello, jaceEmail

    private String phoneNumber;

    private LocalDateTime creationDate = LocalDateTime.now();



}
