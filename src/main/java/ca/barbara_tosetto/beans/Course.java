package ca.barbara_tosetto.beans;

import lombok.Data;

@Data
public class Course {
    private Long id;
    private String name;
    private Long grade;
    private Long studentId;

    public Course() {
    }

    public Course(Long id, String name, Long grade, Long studentId) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.studentId = studentId;
    }
}
