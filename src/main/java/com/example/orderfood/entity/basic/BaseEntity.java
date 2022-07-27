package com.example.orderfood.entity.basic;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();
    @LastModifiedDate
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private Long createdBy;
    private Long updatedBy;
    private Long deletedBy;
}
