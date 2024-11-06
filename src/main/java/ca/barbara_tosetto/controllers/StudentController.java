package ca.barbara_tosetto.controllers;

import ca.barbara_tosetto.beans.Course;
import ca.barbara_tosetto.beans.Student;
import ca.barbara_tosetto.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private DatabaseAccess databaseAccess;

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Student student = databaseAccess.findStudentById(id);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<List<Course>> getCoursesByStudentId(@PathVariable Long id) {
        List<Course> courses = databaseAccess.findCoursesByStudentId(id);
        if (courses == null || courses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(courses);
    }

    @PostMapping("/courses")
    public ResponseEntity<String> addCourse(@RequestBody Course course) {
        try {
            databaseAccess.addCourse(course);
            return ResponseEntity.status(HttpStatus.CREATED).body("Course added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error adding course: " + e.getMessage());
        }
    }
}


