package com.example.finalprojectapi.payload.teacher;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DeleteTeacherRequest {

    @NotNull
    @Valid
    @JsonProperty("teacher_ids")
    private ArrayList<Long> teacherIds;

    @Builder
    public DeleteTeacherRequest(@NotNull @Valid ArrayList<Long> teacherIds) {
        this.teacherIds = teacherIds;
    }
}
