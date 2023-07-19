package com.mentoresalumnos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "mentors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mentor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "apellido")
    private String lastName;

    @Column(name = "edad")
    private Integer age;

    @Column(name = "tiempo_experiencia")
    private Integer experienceTime;

    @ManyToMany(mappedBy = "mentors")
    private List<Student> students;

}
