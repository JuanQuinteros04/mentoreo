package com.mentoresalumnos.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "mentors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mentor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "experience_time")
    private Integer experienceTime;

    @Column(name = "location")
    private String location;

    @ManyToMany(mappedBy = "mentors")
    private List<Student> students;

}
