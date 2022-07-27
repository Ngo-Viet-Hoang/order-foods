package com.example.orderfood.entity.entityEnum;

public enum FoodStatus {

    SALE(1), UNSALE(0), DELETED(-1), STOP(-2);

    private int value;

    FoodStatus (int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static FoodStatus of(int value) {
        for (FoodStatus  status :
                FoodStatus .values()) {
            if(status.getValue() == value){
                return status;
            }
        }
        return FoodStatus .SALE;
    }
}
