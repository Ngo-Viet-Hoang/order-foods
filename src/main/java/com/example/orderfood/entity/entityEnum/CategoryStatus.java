package com.example.orderfood.entity.entityEnum;

public enum CategoryStatus {
    SALE(1), UNSALE(0), DELETED(-1), STOP(-2);

    private int value;

    CategoryStatus (int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CategoryStatus of(int value) {
        for (CategoryStatus  categoryStatus :
                CategoryStatus .values()) {
            if(categoryStatus.getValue() == value){
                return categoryStatus;
            }
        }
        return CategoryStatus .UNSALE;
    }
}
