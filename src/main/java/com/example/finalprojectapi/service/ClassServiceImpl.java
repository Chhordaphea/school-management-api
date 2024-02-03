package com.example.finalprojectapi.service;

import com.example.finalprojectapi.common.api.StatusCode;
import com.example.finalprojectapi.domain.classes.*;
import com.example.finalprojectapi.domain.classes.Class;
import com.example.finalprojectapi.domain.student.StudentRepository;
import com.example.finalprojectapi.domain.teacher.TeacherRepository;
import com.example.finalprojectapi.enums.Status;
import com.example.finalprojectapi.exception.BusinessException;
import com.example.finalprojectapi.helper.AuthHelper;
import com.example.finalprojectapi.payload.ClassMainResponse;
import com.example.finalprojectapi.payload.ClassRequest;
import com.example.finalprojectapi.payload.ClassResponse;
import com.example.finalprojectapi.payload.student.StudentList;
import com.example.finalprojectapi.payload.student.StudentMainResponse;
import com.example.finalprojectapi.payload.teacher.TeacherList;
import com.example.finalprojectapi.payload.teacher.TeacherMainResponse;
import com.example.finalprojectapi.properties.FileInfoConfig;
import com.example.finalprojectapi.utils.BaseSpecification;
import com.example.finalprojectapi.utils.ImageUtil;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService{

    private final ClassRepository classRepository;

    private final TeacherClassRepository teacherClassRepository;

    private final StudentClassRepository studentClassRepository;

    private final TeacherRepository teacherRepository;

    private final StudentRepository studentRepository;

    private final FileInfoConfig fileInfoConfig;
    @Override
    public Object getClasses(String searchValue, Pageable pageable) {
        var classesPage = classRepository.findAll((root, query, criteriaBuilder) -> {
            ArrayList<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(searchValue)) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), BaseSpecification.containUpperCase(searchValue)));
            }
            predicates.add(criteriaBuilder.equal(root.get("users"), AuthHelper.getUser()));
            predicates.add(criteriaBuilder.equal(root.get("status"), Status.NORMAL));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageable);

        var classResponse = classesPage.stream()
                .map(x -> ClassResponse.builder()
                        .id(x.getId())
                        .name(x.getName())
                        .createdAt(x.getCreatedAt())
                        .build())
                .toList();

        return ClassMainResponse.builder()
                .classLists(classResponse)
                .page(classesPage)
                .build();
    }

    @Override
    @Transactional
    public void createClass(ClassRequest payload) {
        var classes = Class.builder()
                .name(payload.getName())
                .users(AuthHelper.getUser())
                .status(Status.NORMAL)
                .build();
        var classEntity = classRepository.save(classes);

      var teacherClass  = payload.getTeacherList().stream()
               .map(x -> TeacherClass.builder()
                       .classId(classEntity.getId())
                        .teacherId(x)
                       .build())
               .collect(Collectors.toList());
        teacherClassRepository.saveAll(teacherClass);

        var studentClass = payload.getStudentList().stream()
                .map(x -> StudentClass.builder()
                        .classId(classEntity.getId())
                        .studentId(x)
                        .build())
                .collect(Collectors.toList());
        studentClassRepository.saveAll(studentClass);
    }

    @Override
    public Object getTeacherClasses(Long classId,Pageable pageable) {
        var teacherClasses = teacherClassRepository.findAllByClassId(classId,pageable);
       var response = teacherClasses.stream()
                .map(x -> {
                    var teacher = teacherRepository.findByIdAndStatusAndUser(x.getTeacherId(),AuthHelper.getUser(),Status.NORMAL).orElseThrow(() -> new BusinessException(StatusCode.TEACHER_NOT_FOUND));
                    return TeacherList.builder()
                            .createdAt(teacher.getCreatedAt())
                            .id(teacher.getId())
                            .name(teacher.getName())
                            .address(teacher.getAddress())
                            .phoneNumber(teacher.getPhoneNumber())
                            .profileImage(ImageUtil.getImageUrl(fileInfoConfig.getBaseUrl(),teacher.getProfilePicture()))
                            .build();
                        }
                    )
                .collect(Collectors.toList());

        return TeacherMainResponse.builder()
                .teacherLists(response)
                .page(teacherClasses)
                .build();
    }

    @Override
    public Object getStudentClasses(Long classId,Pageable pageable) {
        var studentClasses = studentClassRepository.findAllByClassId(classId,pageable);
        var response = studentClasses.stream()
                .map(x -> {
                    var student = studentRepository.findByIdAndStatusAndUser(x.getStudentId(),AuthHelper.getUser(),Status.NORMAL).orElseThrow(() -> new BusinessException(StatusCode.STUDENT_NOT_FOUND));
                    return StudentList.builder()
                            .createdAt(student.getCreatedAt())
                            .id(student.getId())
                            .name(student.getName())
                            .address(student.getAddress())
                            .gender(student.getGender())
                            .phoneNumber(student.getPhoneNumber())
                            .profileImage(ImageUtil.getImageUrl(fileInfoConfig.getBaseUrl(),student.getProfilePicture()))
                            .build();
                        }
                    )
                .collect(Collectors.toList());

        return StudentMainResponse.builder()
                .studentList(response)
                .page(studentClasses)
                .build();
    }
}
