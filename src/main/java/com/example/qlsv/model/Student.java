package com.example.qlsv.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "student")
@JsonIgnoreProperties("studentClasses")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int mssv;
    private String studentName;
    private int age;
    private String studentMajor;
    private String hometownAddress;

    @ManyToMany(mappedBy = "students")
    private List<StudentClass> studentClasses;

    public Student() {this.studentClasses = new ArrayList<>();}

}
