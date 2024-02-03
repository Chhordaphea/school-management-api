package com.example.finalprojectapi.payload.student;

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
public class DeleteStudentRequest {

    @NotNull
    @Valid
    @JsonProperty("student_ids")
    private ArrayList<Long> studentIds;

    @Builder
    public DeleteStudentRequest(ArrayList<Long> studentIds) {
        this.studentIds = studentIds;
    }
}
