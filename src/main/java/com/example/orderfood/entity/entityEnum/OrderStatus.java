package com.example.orderfood.entity.entityEnum;

public  enum OrderStatus {
    PENDING(1),DONE(-1);

    private int value;

    OrderStatus (int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static OrderStatus of(int value) {
        for (OrderStatus  orderStatus :
                OrderStatus .values()) {
            if(orderStatus.getValue() == value){
                return orderStatus;
            }
        }
        return OrderStatus .PENDING;
    }



}
