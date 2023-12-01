package com.example.qlsv.service;

import com.example.qlsv.model.Student;
import com.example.qlsv.model.StudentClass;
import com.example.qlsv.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService{
    private final StudentRepository studentRepository;
    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    @Override
    public Optional<Student> getStudentById(Long studentId) {
        return studentRepository.findById(studentId);
    }
    @Override
    public List<StudentClass> getClassesByStudentId(Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        return student.map(Student::getStudentClasses).orElse(Collections.emptyList());
    }
    public Student getStudentByUsername(String username) {
        // Triển khai phương thức để truy vấn sinh viên từ username
        // Return null nếu không tìm thấy
        return studentRepository.findByStudentName(username).orElse(null);
    }
    @Override
    public void createStudent(Student student) {
        studentRepository.save(student);
    }
}
