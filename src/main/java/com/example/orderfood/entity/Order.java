package com.example.orderfood.entity;

import com.example.orderfood.entity.basic.BaseEntity;
import com.example.orderfood.entity.entityEnum.OrderStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;
    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();
    private BigDecimal totalPrice = BigDecimal.ZERO;
    @Enumerated(EnumType.ORDINAL)
    private OrderStatus status;

    @OneToMany(mappedBy = "order",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<OrderDetail> orderDetails;

//    @PostPersist
//    public void updateSlug() {
//        System.out.println("Before save");
//        System.out.println(id);
//    }
//
//    @PostUpdate
//    public void afterSave() {
//        System.out.println("After save");
//        System.out.println(id);
//    }

    public void calTotalPrice(List<OrderDetail> details) {
        details.forEach( e -> {
            this.totalPrice = this.totalPrice.add(e.getUnitPrice().multiply(new BigDecimal(e.getQuantity())));
        });
    }
}
