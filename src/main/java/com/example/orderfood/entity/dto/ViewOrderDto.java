package com.example.orderfood.entity.dto;

import com.example.orderfood.entity.ViewOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewOrderDto {
    private BigDecimal totalPrice = BigDecimal.ZERO;
    private List<ViewOrder> viewOrders;

    public void calTotalPrice(List<ViewOrder> viewOrders){
        viewOrders.forEach(e ->{
            this.totalPrice = this.totalPrice.add(e.getFood().getPrice().multiply(new BigDecimal(e.getQuantity())));
        });
    }
}
