package com.example.finalprojectapi.payload.student;

import com.example.finalprojectapi.payload.Pagination;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StudentMainResponse {

   private List<StudentList> studentList;

    Pagination pagination;

    @Builder
    public StudentMainResponse(List<StudentList> studentList, Page<?> page) {
        this.studentList = studentList;
        this.pagination = new Pagination(page);
    }


}
