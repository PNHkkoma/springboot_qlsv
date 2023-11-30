package com.example.qlsv.repository;

import com.example.qlsv.model.Student;
import com.example.qlsv.model.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRepository extends JpaRepository<StudentClass, Long> {
    List<StudentClass> findByStudentsId(Long studentId);
}
