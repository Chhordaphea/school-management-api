package com.example.finalprojectapi.payload;

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
public class ClassMainResponse {

   private List<ClassResponse> classLists;

    Pagination pagination;

    @Builder
    public ClassMainResponse(List<ClassResponse> classLists, Page<?> page) {
        this.classLists = classLists;
        this.pagination = new Pagination(page);
    }


}
