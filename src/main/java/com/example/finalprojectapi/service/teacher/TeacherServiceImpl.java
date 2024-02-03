package com.example.finalprojectapi.service.teacher;

import com.example.finalprojectapi.common.api.StatusCode;
import com.example.finalprojectapi.domain.teacher.Teacher;
import com.example.finalprojectapi.domain.teacher.TeacherRepository;
import com.example.finalprojectapi.enums.Status;
import com.example.finalprojectapi.exception.BusinessException;
import com.example.finalprojectapi.helper.AuthHelper;
import com.example.finalprojectapi.payload.student.StudentList;
import com.example.finalprojectapi.payload.student.StudentMainResponse;
import com.example.finalprojectapi.payload.teacher.DeleteTeacherRequest;
import com.example.finalprojectapi.payload.teacher.TeacherList;
import com.example.finalprojectapi.payload.teacher.TeacherMainResponse;
import com.example.finalprojectapi.payload.teacher.TeacherRequest;
import com.example.finalprojectapi.properties.FileInfoConfig;
import com.example.finalprojectapi.utils.BaseSpecification;
import com.example.finalprojectapi.utils.ImageUtil;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    private final FileInfoConfig fileInfoConfig;

    @Override
    public Object getTeachers(String searchValue, String startDate, String endDate, Pageable pageable) {
        Page<Teacher> teacherPage = teacherRepository.findAll((root, query, criteriaBuilder) -> {
            ArrayList<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(searchValue)) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), BaseSpecification.containUpperCase(searchValue)));
            }
            if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)) {
                predicates.add(criteriaBuilder.between(root.get("createdAt"), startDate, endDate));
            }
            predicates.add(criteriaBuilder.equal(root.get("users"), AuthHelper.getUser()));
            predicates.add(criteriaBuilder.equal(root.get("status"), Status.NORMAL));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageable);

        var teacherResponse = teacherPage.stream()
                .map(x -> TeacherList.builder()
                        .id(x.getId())
                        .name(x.getName())
                        .gender(x.getGender())
                        .address(x.getAddress())
                        .phoneNumber(x.getPhoneNumber())
                        .email(x.getEmail())
                        .status(x.getStatus())
                        .createdAt(x.getCreatedAt())
                        .profileImage(ImageUtil.getImageUrl(fileInfoConfig.getBaseUrl(),x.getProfilePicture()))
                        .build()).toList();

        return TeacherMainResponse.builder()
                .teacherLists(teacherResponse)
                .page(teacherPage)
                .build();
    }

    @Override
    public void createTeachers(TeacherRequest payload) {
        var teacher = Teacher.builder()
                .name(payload.getName())
                .gender(payload.getGender())
                .address(payload.getAddress())
                .phoneNumber(payload.getPhoneNumber())
                .email(payload.getEmail())
                .status(Status.NORMAL)
                .users(AuthHelper.getUser())
                .profilePicture(payload.getProfileImage())
                .build();
        teacherRepository.save(teacher);
    }

    @Override
    public void updateTeachers(TeacherRequest payload, Long id) {
        var teacher = teacherRepository.findByIdAndStatusAndUser(id,AuthHelper.getUser(),Status.NORMAL).orElseThrow(() -> new BusinessException(StatusCode.TEACHER_NOT_FOUND));
        teacher.setGender(payload.getGender());
        teacher.setAddress(payload.getAddress());
        teacher.setName(payload.getName());
        teacher.setPhoneNumber(payload.getPhoneNumber());

        if (StringUtils.isNotBlank(payload.getEmail())) {
            teacher.setEmail(payload.getEmail());
        }
        if (StringUtils.isNotBlank(payload.getProfileImage())) {
            teacher.setProfilePicture(payload.getProfileImage());
        }
        teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeachers(DeleteTeacherRequest payload) {
        payload.getTeacherIds().forEach(id -> {
            var teacher = teacherRepository.findByIdAndStatusAndUser(id,AuthHelper.getUser(),Status.NORMAL).orElseThrow(() -> new BusinessException(StatusCode.TEACHER_NOT_FOUND));
            teacher.setStatus(Status.DISABLE);
            teacherRepository.save(teacher);
        });
    }
}
