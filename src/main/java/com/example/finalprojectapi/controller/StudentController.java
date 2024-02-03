package com.example.finalprojectapi.controller;

import com.example.finalprojectapi.payload.MultiSortBuilder;
import com.example.finalprojectapi.payload.student.DeleteStudentRequest;
import com.example.finalprojectapi.payload.student.StudentRequest;
import com.example.finalprojectapi.service.student.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/bo/v1")
@RequiredArgsConstructor
public class StudentController extends RestControllerConfig {

    private final StudentService studentService;

    @GetMapping("/students")
    public Object getAllStudents(
            @RequestParam(value = "search_value", required = false) String searchValue,
            @RequestParam(value = "start_date", required = false) String startDate,
            @RequestParam(value = "end_date", required = false) String endDate,
            @RequestParam(value = "sort_columns", required = false, defaultValue = "id:desc") String sortColumns,
            @RequestParam(value = "page_number", defaultValue = "0") int pageNumber,
            @RequestParam(value = "page_size", defaultValue = "10") int pageSize) throws Throwable {
        List<Sort.Order> sortBuilder = new MultiSortBuilder().with(sortColumns).build();
        Pageable pages = PageRequest.of(pageNumber, pageSize, Sort.by(sortBuilder));
        return ok(studentService.getStudents(searchValue, startDate, endDate, pages));
    }

    @PostMapping("/students")
    public ResponseEntity<?> createStudent(@Valid @RequestBody StudentRequest payload) throws Throwable {
        studentService.createStudent(payload);
        return ok();
    }

    @DeleteMapping("/students")
    public ResponseEntity<?> deleteStudents(@Valid @RequestBody DeleteStudentRequest payload) throws Throwable {
        studentService.deleteStudent(payload);
        return ok();
    }


    @PatchMapping("/students/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable("id") Long id, @Valid @RequestBody StudentRequest payload) throws Throwable {
        studentService.updateStudent(payload,id);
        return ok();
    }


}
