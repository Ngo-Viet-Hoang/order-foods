package com.example.orderfood.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ResponseData {

    private String message;

    private  int status;

    private Object data;

    public ResponseData() {
    }

    public ResponseData(String message, int status, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }
}
