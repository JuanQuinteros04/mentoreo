package com.mentoresalumnos.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MentorDTO {

    private String name;

    private String lastName;

    private Integer age;

    private Integer experienceTime;

}
