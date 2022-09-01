package com.example.sgswimming.DTOs;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstructorFatDto {
    private Long id;
    private String firstName;
    private String lastName;

    @Singular
    private List<LessonSkinnyDto> lessons;
}
