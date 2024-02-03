package com.example.finalprojectapi.controller;

import com.example.finalprojectapi.payload.ClassRequest;
import com.example.finalprojectapi.payload.MultiSortBuilder;
import com.example.finalprojectapi.service.ClassService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bo/v1")
@RequiredArgsConstructor
public class ClassController extends RestControllerConfig {

    private final ClassService classService;

    @GetMapping("/classes")
    public Object getAllStudents(
            @RequestParam(value = "search_value", required = false) String searchValue,
            @RequestParam(value = "sort_columns", required = false, defaultValue = "id:desc") String sortColumns,
            @RequestParam(value = "page_number", defaultValue = "0") int pageNumber,
            @RequestParam(value = "page_size", defaultValue = "10") int pageSize) throws Throwable {
        List<Sort.Order> sortBuilder = new MultiSortBuilder().with(sortColumns).build();
        Pageable pages = PageRequest.of(pageNumber, pageSize, Sort.by(sortBuilder));
        return ok(classService.getClasses(searchValue,pages));
    }

    @PostMapping("/classes")
    public ResponseEntity<?> createClasses(@Valid @RequestBody ClassRequest payload) throws Throwable {
        classService.createClass(payload);
        return ok();
    }

    @GetMapping("/classes/{id}/teachers")
    public Object getTeacherClass(
            @PathVariable("id") Long classId,
            @RequestParam(value = "sort_columns", required = false, defaultValue = "teacherId:desc") String sortColumns,
            @RequestParam(value = "page_number", defaultValue = "0") int pageNumber,
            @RequestParam(value = "page_size", defaultValue = "10") int pageSize) throws Throwable {
        List<Sort.Order> sortBuilder = new MultiSortBuilder().with(sortColumns).build();
        Pageable pages = PageRequest.of(pageNumber, pageSize, Sort.by(sortBuilder));
        return ok(classService.getTeacherClasses(classId,pages));
    }

    @GetMapping("/classes/{id}/students")
    public Object getStudentClass(
            @PathVariable("id") Long classId,
            @RequestParam(value = "sort_columns", required = false, defaultValue = "studentId:desc") String sortColumns,
            @RequestParam(value = "page_number", defaultValue = "0") int pageNumber,
            @RequestParam(value = "page_size", defaultValue = "10") int pageSize) throws Throwable {
        List<Sort.Order> sortBuilder = new MultiSortBuilder().with(sortColumns).build();
        Pageable pages = PageRequest.of(pageNumber, pageSize, Sort.by(sortBuilder));
        return ok(classService.getStudentClasses(classId,pages));
    }

}
