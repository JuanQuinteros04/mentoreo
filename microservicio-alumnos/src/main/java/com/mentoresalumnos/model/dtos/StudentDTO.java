package com.mentoresalumnos.model.dtos;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDTO {

    private String name;

    private String lastName;

    private Integer age;

    private String studentLevel;


}
