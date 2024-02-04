package com.example.finalprojectapi.payload.dashboard;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DashboardResponse {

    private Long totalTeacher;

    private Long totalStudent;

    private Long totalClass;

    @Builder
    public DashboardResponse(Long totalTeacher, Long totalStudent, Long totalClass) {
        this.totalTeacher = totalTeacher;
        this.totalStudent = totalStudent;
        this.totalClass = totalClass;
    }
}
