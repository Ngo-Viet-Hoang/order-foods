package com.example.orderfood.entity;

import com.example.orderfood.entity.basic.BaseEntity;
import com.example.orderfood.entity.entityEnum.FoodStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name= "foods")
public class Food extends BaseEntity{
    @Id
    private String id;
    private String name;
    private String slug;
    private String image;
    private BigDecimal price;
    private String description;
    @Enumerated(EnumType.ORDINAL)
    private FoodStatus status;


    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

}
