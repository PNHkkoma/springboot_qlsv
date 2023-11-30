package com.example.qlsv.service;

import com.example.qlsv.model.Student;
import com.example.qlsv.model.StudentClass;
import com.example.qlsv.repository.ClassRepository;
import com.example.qlsv.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClassService implements IClassService {
    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;
    @Override
    public List<StudentClass> getAllClasses() {
        return classRepository.findAll();
    }

    @Override
    public Optional<StudentClass> getClassById(Long classId) {
        return classRepository.findById(classId);
    }

    @Override
    public List<Student> getStudentsByClassId(Long classId) {
        Optional<StudentClass> studentClass = classRepository.findById(classId);
        return studentClass.map(StudentClass::getStudents).orElse(Collections.emptyList());
    }

    @Override
    public void createClass(StudentClass studentClass) {
        classRepository.save(studentClass);
    }

    public void addStudentToClass(Long studentId, Long classId) {
        StudentClass studentClass = classRepository.findById(classId)
                .orElseThrow(() -> new RuntimeException("Class not found"));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Thêm sinh viên vào lớp
        studentClass.addStudent(student);
        classRepository.save(studentClass);
    }

}
