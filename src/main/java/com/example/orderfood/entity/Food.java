package com.example.orderfood.entity;

import com.example.orderfood.entity.basic.BaseEntity;
import com.example.orderfood.entity.entityEnum.FoodStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name= "foods")
public class Food {
    @Id
    private String id;
    private String name;
    private String slug;
    private String image;
    private BigDecimal price;
    private String description;
    @Enumerated(EnumType.ORDINAL)
    private FoodStatus status;
    private LocalDateTime mealTime;
    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

}
