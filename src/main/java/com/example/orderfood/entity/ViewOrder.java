package com.example.orderfood.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "view_orders")
public class ViewOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;
   @OneToOne
   @JoinColumn(name = "foodId")
   private Food food;
   private int quantity;
    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();


}
