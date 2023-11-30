package com.example.qlsv.repository;

import com.example.qlsv.model.Student;
import com.example.qlsv.model.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByStudentClassesId(Long classId);
}
