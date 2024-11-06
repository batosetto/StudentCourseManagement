package ca.barbara_tosetto.controllers;

import ca.barbara_tosetto.beans.Course;
import ca.barbara_tosetto.beans.Student;
import ca.barbara_tosetto.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private DatabaseAccess databaseAccess;

    @GetMapping("/")
    public String index(Model model) {
        List<Student> students = databaseAccess.findAllStudents();
        model.addAttribute("students", students);
        return "index";
    }

    @GetMapping("/view-student")
    public String viewStudent(@RequestParam("id") Long id, Model model) {
        Student student = databaseAccess.findStudentById(id);
        if (student == null) {
            model.addAttribute("errorMessage", "Student not found");
            return "error";
        }
        List<Course> courses = databaseAccess.findCoursesByStudentId(id);
        model.addAttribute("student", student);
        model.addAttribute("courses", courses);
        return "view-student";
    }

    @GetMapping("/add-course")
    public String addCourse(@RequestParam("studentId") Long studentId, Model model) {
        Student student = databaseAccess.findStudentById(studentId);
        if (student == null) {
            model.addAttribute("errorMessage", "Student not found");
            return "error";
        }
        model.addAttribute("student", student);
        model.addAttribute("course", new Course());
        return "add-course";
    }

    @PostMapping("/add-course")
    public String saveCourse(@RequestParam("studentId") Long studentId, Course course, Model model) {
        Student student = databaseAccess.findStudentById(studentId);
        if (student == null) {
            model.addAttribute("errorMessage", "Student not found");
            return "error";
        }
        course.setStudentId(studentId);
        databaseAccess.addCourse(course);
        return "redirect:/view-student?id=" + studentId;
    }
}


