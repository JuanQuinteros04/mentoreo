package com.mentoresalumnos.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "student_level")
    private String studentLevel;

    @Column(name = "number_mentors")
    private Integer numberMentors;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "student_mentor",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "mentor_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"student_id", "mentor_id"})}
    )
    private List<Mentor> mentors;


}
