package com.example.qlsv.controler;

import com.example.qlsv.model.Student;
import com.example.qlsv.model.StudentClass;
import com.example.qlsv.service.ClassService;
import com.example.qlsv.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/classes")
public class ClassController {
    private final ClassService classService;
    private final StudentService studentService;

    // Constructor...

    @GetMapping("/all")
    public List<StudentClass> getAllClasses() {
        return classService.getAllClasses();
    }

    @GetMapping("/{classId}")
    public ResponseEntity<StudentClass> getClassById(@PathVariable Long classId) {
        Optional<StudentClass> studentClass = classService.getClassById(classId);
        return studentClass.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/addNewClass")
    public ResponseEntity<String> createStudentClass(@RequestBody StudentClass studentClass) {
        try {
            classService.createClass(studentClass);
            return ResponseEntity.ok("Student class created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating student class.");
        }
    }

    @GetMapping("/{classId}/students")
    public List<Student> getStudentsByClassId(@PathVariable Long classId) {
        return classService.getStudentsByClassId(classId);
    }

    @PostMapping("/{classId}/students/{studentId}")
    public ResponseEntity<String> addStudentToClass(
            @PathVariable Long classId,
            @PathVariable Long studentId) {
        try {
            classService.addStudentToClass(studentId, classId);
            return ResponseEntity.ok("Student added to class successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding student to class.");
        }
    }
}
