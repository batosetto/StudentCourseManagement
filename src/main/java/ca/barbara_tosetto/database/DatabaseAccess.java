package ca.barbara_tosetto.database;

import ca.barbara_tosetto.beans.Course;
import ca.barbara_tosetto.beans.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabaseAccess {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<Student> findAllStudents() {
        String query = "SELECT * FROM students";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Student.class));
    }

    public Student findStudentById(Long id) {
        String query = "SELECT * FROM students WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        List<Student> students = jdbcTemplate.query(query, params, new BeanPropertyRowMapper<>(Student.class));
        if (students.isEmpty()) {
            return null;
        } else {
            Student student = students.get(0);
            student.setCourses(findCoursesByStudentId(id));
            return student;
        }
    }

    public List<Course> findCoursesByStudentId(Long studentId) {
        String query = "SELECT * FROM courses WHERE studentId = :studentId";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("studentId", studentId);
        return jdbcTemplate.query(query, params, new BeanPropertyRowMapper<>(Course.class));
    }

    public void addCourse(Course course) {
        String query = "INSERT INTO courses (name, grade, studentId) VALUES (:name, :grade, :studentId)";
        SqlParameterSource params = new BeanPropertySqlParameterSource(course);
        jdbcTemplate.update(query, params);
    }



}
