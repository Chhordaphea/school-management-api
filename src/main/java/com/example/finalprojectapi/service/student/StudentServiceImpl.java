package com.example.finalprojectapi.service.student;

import com.example.finalprojectapi.common.api.StatusCode;
import com.example.finalprojectapi.domain.student.Student;
import com.example.finalprojectapi.domain.student.StudentRepository;
import com.example.finalprojectapi.enums.Status;
import com.example.finalprojectapi.exception.BusinessException;
import com.example.finalprojectapi.helper.AuthHelper;
import com.example.finalprojectapi.payload.student.DeleteStudentRequest;
import com.example.finalprojectapi.payload.student.StudentList;
import com.example.finalprojectapi.payload.student.StudentMainResponse;
import com.example.finalprojectapi.payload.student.StudentRequest;
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
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    private final FileInfoConfig fileInfoConfig;

    @Override
    public Object getStudents(String searchValue, String startDate, String endDate, Pageable pageable) {
        var studentPage = studentRepository.findAll((root, query, criteriaBuilder) -> {
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

        var studentResponse = studentPage.stream()
                .map(student -> StudentList.builder()
                        .id(student.getId())
                        .name(student.getName())
                        .gender(student.getGender())
                        .address(student.getAddress())
                        .phoneNumber(student.getPhoneNumber())
                        .profileImage(ImageUtil.getImageUrl(fileInfoConfig.getBaseUrl(),student.getProfilePicture()))
                        .email(student.getEmail())
                        .status(student.getStatus())
                        .createdAt(student.getCreatedAt())
                        .build()).toList();

        return StudentMainResponse.builder()
                .studentList(studentResponse)
                .page(studentPage)
                .build();
    }

    @Override
    public void createStudent(StudentRequest payload) {
        var student = Student.builder()
                .name(payload.getName())
                .gender(payload.getGender())
                .address(payload.getAddress())
                .phoneNumber(payload.getPhoneNumber())
                .email(payload.getEmail())
                .status(Status.NORMAL)
                .users(AuthHelper.getUser())
                .profilePicture(payload.getProfileImage())
                .build();
        studentRepository.save(student);
    }

    @Override
    public void updateStudent(StudentRequest payload, Long id) {
        var student = studentRepository.findByIdAndStatusAndUser(id,AuthHelper.getUser(),Status.NORMAL).orElseThrow(() -> new BusinessException(StatusCode.STUDENT_NOT_FOUND));
        student.setName(payload.getName());
        student.setGender(payload.getGender());
        student.setAddress(payload.getAddress());
        student.setPhoneNumber(payload.getPhoneNumber());
        if (StringUtils.isNotBlank(payload.getEmail())) {
            student.setEmail(payload.getEmail());
        }
        if(StringUtils.isNotBlank(payload.getProfileImage())){
            student.setProfilePicture(payload.getProfileImage());
        }
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(DeleteStudentRequest payload) {
        payload.getStudentIds().forEach(id -> {
            var student = studentRepository.findByIdAndStatusAndUser(id,AuthHelper.getUser(),Status.NORMAL).orElseThrow(() -> new BusinessException(StatusCode.STUDENT_NOT_FOUND));
            student.setStatus(Status.DISABLE);
            studentRepository.save(student);
        });
    }
}
