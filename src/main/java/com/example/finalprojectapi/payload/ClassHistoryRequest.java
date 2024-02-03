package com.example.finalprojectapi.payload;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ClassHistoryRequest {


    @NotNull
    private Long teacherId;

    @NotNull
    private Long studentId;

   @Builder
    public ClassHistoryRequest( Long teacherId, Long studentId) {
        this.teacherId = teacherId;
        this.studentId = studentId;
    }


}
