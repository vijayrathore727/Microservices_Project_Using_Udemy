package com.example.accounts.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@MappedSuperclass
public class BaseEntity {

    @Column(updatable = false)
    private LocalDate createdAt;

    @Column(updatable = false)
    private String createdBy;

    @Column(updatable = false)
    private LocalDateTime updatedAt;

    @Column(updatable = false)
    private String updatedBy;
}
