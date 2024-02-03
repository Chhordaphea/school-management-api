package com.example.finalprojectapi.controller;

import com.example.finalprojectapi.service.dashboard.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bo/v1")
@RequiredArgsConstructor
public class DashboardController extends RestControllerConfig{

    private final DashboardService dashboardService;

    @GetMapping("/summary")
    public Object getDashboardData(){
        return ok(dashboardService.getDashboardData());
    }
}
