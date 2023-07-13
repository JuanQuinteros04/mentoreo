package com.mentoresalumnos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "mentores")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mentor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "tiempo_experiencia")
    private Integer tiempoExperiencia;

    @ManyToMany(mappedBy = "mentores")
    private List<Alumno> alumnos;

}
