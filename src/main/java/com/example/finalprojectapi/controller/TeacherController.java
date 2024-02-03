package com.example.finalprojectapi.controller;

import com.example.finalprojectapi.payload.MultiSortBuilder;
import com.example.finalprojectapi.payload.teacher.DeleteTeacherRequest;
import com.example.finalprojectapi.payload.teacher.TeacherRequest;
import com.example.finalprojectapi.service.teacher.TeacherService;
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
public class TeacherController extends RestControllerConfig {

    private final TeacherService teacherService;

    @GetMapping("/teachers")
    public Object getAllStudents(
            @RequestParam(value = "search_value", required = false) String searchValue,
            @RequestParam(value = "start_date", required = false) String startDate,
            @RequestParam(value = "end_date", required = false) String endDate,
            @RequestParam(value = "sort_columns", required = false, defaultValue = "id:desc") String sortColumns,
            @RequestParam(value = "page_number", defaultValue = "0") int pageNumber,
            @RequestParam(value = "page_size", defaultValue = "10") int pageSize) throws Throwable {
        List<Sort.Order> sortBuilder = new MultiSortBuilder().with(sortColumns).build();
        Pageable pages = PageRequest.of(pageNumber, pageSize, Sort.by(sortBuilder));
        return ok(teacherService.getTeachers(searchValue, startDate, endDate, pages));
    }

    @PostMapping("/teachers")
    public ResponseEntity<?> createStudent(@Valid @RequestBody TeacherRequest payload) throws Throwable {
       teacherService.createTeachers(payload);
        return ok();
    }
    @PatchMapping("/teachers/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable("id") Long id, @Valid @RequestBody TeacherRequest payload) throws Throwable {
        teacherService.updateTeachers(payload,id);
        return ok();
    }


    @DeleteMapping("/teachers")
    public ResponseEntity<?> deleteStudents(@Valid @RequestBody DeleteTeacherRequest payload) throws Throwable {
        teacherService.deleteTeachers(payload);
        return ok();
    }




}
