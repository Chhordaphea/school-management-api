package com.example.finalprojectapi.domain.classes;

import com.example.finalprojectapi.domain.CreatableEntity;
import com.example.finalprojectapi.domain.user.User;
import com.example.finalprojectapi.enums.Status;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "tb_classes")
@Entity
public class Class extends CreatableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(name = "sts", nullable = false, length = Types.CHAR)
    @JdbcTypeCode(Types.CHAR)
    @Convert(converter = Status.Converter.class)
    private Status status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User users;

    @Builder
    public Class(Long id, String name, Status status, User users) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.users = users;
    }

}
