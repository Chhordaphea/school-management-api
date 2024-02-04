package com.example.finalprojectapi.service.dashboard;

import com.example.finalprojectapi.domain.user.UserRepository;
import com.example.finalprojectapi.helper.AuthHelper;
import com.example.finalprojectapi.payload.dashboard.DashboardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService{

    private final UserRepository userRepository;
    @Override
    public Object getDashboardData(){
        var data = userRepository.getDashboardData(AuthHelper.getUserId());
        return DashboardResponse.builder()
                .totalTeacher(data.getTotalTeacher())
                .totalStudent(data.getTotalStudent())
                .totalClass(data.getTotalClass())
                .build();
    }
}
