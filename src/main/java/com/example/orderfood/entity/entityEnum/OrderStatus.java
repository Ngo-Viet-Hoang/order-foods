package com.example.orderfood.entity.entityEnum;

public enum OrderStatus {
    PENDING(1), PROCESSING(0), DONE(-1), CANCEL(-2);

    private int value;

    OrderStatus (int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static OrderStatus of(int value) {
        for (OrderStatus  status :
                OrderStatus .values()) {
            if(status.getValue() == value){
                return status;
            }
        }
        return OrderStatus .PENDING;
    }
}
