package com.example.finalprojectapi.payload.teacher;

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
public class TeacherMainResponse {

   private List<TeacherList> teacherLists;

    Pagination pagination;

    @Builder
    public TeacherMainResponse(List<TeacherList> teacherLists, Page<?> page) {
        this.teacherLists = teacherLists;
        this.pagination = new Pagination(page);
    }

}
