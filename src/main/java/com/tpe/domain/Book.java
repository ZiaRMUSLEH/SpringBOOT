package com.tpe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="t_book")
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("bookName")       // Name for JSON
    //@Column(name = "bookName")    // Name for DB Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnore()
    private Student student;

}

/*

        {
            "bookName": "Chemistry"
            "---": -----
        }


 */