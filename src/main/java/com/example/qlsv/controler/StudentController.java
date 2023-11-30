package com.example.qlsv.controler;


import com.example.qlsv.model.Student;
import com.example.qlsv.model.StudentClass;
import com.example.qlsv.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    // Constructor...

    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/addNewStudent")
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        try {
            studentService.createStudent(student);
            return ResponseEntity.ok("Student created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating student.");
        }
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
        Optional<Student> student = studentService.getStudentById(studentId);
        return student.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{studentId}/classes")
    public List<StudentClass> getClassesByStudentId(@PathVariable Long studentId) {
        return studentService.getClassesByStudentId(studentId);
    }
}
