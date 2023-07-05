package com.mentoresalumnos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mentor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column()
    private String nombre;

    @Column()
    private String apellido;

    @Column()
    private Long edad;

    @Column()
    @ManyToOne
    private Alumno alumno;

}
