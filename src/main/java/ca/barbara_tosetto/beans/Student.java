package ca.barbara_tosetto.beans;

import lombok.Data;

import java.util.List;

@Data
public class Student {
    private Long id;
    private String name;
    private Long number;
    private List<Course> courses;
}
