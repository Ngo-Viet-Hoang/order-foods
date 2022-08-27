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
@Table(name = "view-orders")
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
   private BigDecimal totalPrice = BigDecimal.ZERO;
    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();

//    public void calTotalPrice(List<ViewOrder> viewOrders) {
//        viewOrders.forEach( e -> {
//            this.totalPrice = this.totalPrice.add(e.getFood().getPrice().multiply(new BigDecimal(e.getQuantity())));
//        });
//    }

}
