package com.example.finalprojectapi.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ClassRequest {

    @NotBlank
    private String name;

    @JsonProperty("teacher_list")
    private List<Long> teacherList;

    @JsonProperty("student_list")
    private List<Long> studentList;

    @Builder
    public ClassRequest(String name, List<Long> teacherList, List<Long> studentList) {
        this.name = name;
        this.teacherList = teacherList;
        this.studentList = studentList;
    }

}
