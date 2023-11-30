package com.example.qlsv.service;

import com.example.qlsv.model.Student;
import com.example.qlsv.model.StudentClass;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    List<Student> getAllStudents();
    Optional<Student> getStudentById(Long studentId);
    List<StudentClass> getClassesByStudentId(Long studentId);
    void createStudent(Student student);
    //List<Student> getAllStudent();
    //List<StudentClass> getAllClassJoin(Long studentId);

}
