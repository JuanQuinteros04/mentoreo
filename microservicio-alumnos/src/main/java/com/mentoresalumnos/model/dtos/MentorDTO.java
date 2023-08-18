package com.mentoresalumnos.model.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MentorDTO {

    private String name;

    private String lastName;

    private Integer age;

    private Integer experienceTime;

    private String location;

}
