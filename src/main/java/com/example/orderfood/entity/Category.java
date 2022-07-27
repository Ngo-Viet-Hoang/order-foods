package com.example.orderfood.entity;

import com.example.orderfood.entity.entityEnum.CategoryStatus;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "categories")
public class Category  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private CategoryStatus categoryStatus;
}
