package com.example.orderfood.entity;

import com.example.orderfood.entity.basic.BaseEntity;
import com.example.orderfood.entity.entityEnum.OrderStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    @Id
    private String id;
    private String userId;
    @CreatedDate
    private LocalDateTime createdAt;
    private BigDecimal totalPrice;
    @Enumerated(EnumType.ORDINAL)
    private OrderStatus status;

    @OneToMany(mappedBy = "order",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<OrderDetail> orderDetails;

    @PostPersist
    public void updateSlug() {
        System.out.println("Before save");
        System.out.println(id);
    }

    @PostUpdate
    public void afterSave() {
        System.out.println("After save");
        System.out.println(id);
    }

    public void addTotalPrice(OrderDetail orderDetail) {
        if (this.totalPrice == null) {
            this.totalPrice = new BigDecimal(0);
        }
        BigDecimal quantityInBigDecimal = new BigDecimal(orderDetail.getQuantity());
        this.totalPrice = this.totalPrice.add(
                orderDetail.getUnitPrice().multiply(quantityInBigDecimal));
    }
}
