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
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Column(name = "mentores")
    @OneToMany(fetch = FetchType.LAZY)
    private List<Mentor> mentores;


}
