package com.example.qlsv.repository;

import com.example.qlsv.model.Student;
import com.example.qlsv.model.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByStudentName(String studentName);
}
