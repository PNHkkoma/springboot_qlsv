package com.example.qlsv.service;

import com.example.qlsv.model.Student;
import com.example.qlsv.model.StudentClass;

import java.util.List;
import java.util.Optional;

public interface IClassService {
    List<StudentClass> getAllClasses();
    Optional<StudentClass> getClassById(Long classId);
    List<Student> getStudentsByClassId(Long classId);

    void createClass(StudentClass studentClass);
    void addStudentToClass(Long studentId, Long classId);
    //String saveAddStudent(Long StudentId, StudentClass joinRequest);
    //List<Student> getAllStudentInClass(Long studentId);

}
