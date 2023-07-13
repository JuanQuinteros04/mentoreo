package com.mentoresalumnos.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "alumnos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "nivel")
    private String alumnoNivel;

    @Column(name = "cantidad_mentores")
    private Integer cantidadMentores;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "alumno_mentor",
            joinColumns = @JoinColumn(name = "alumno_id"),
            inverseJoinColumns = @JoinColumn(name = "mentor_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"alumno_id", "mentor_id"})}
    )
    private List<Mentor> mentores;


}
