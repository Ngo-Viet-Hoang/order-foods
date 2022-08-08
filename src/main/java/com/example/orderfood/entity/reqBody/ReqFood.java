package com.example.orderfood.entity.reqBody;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReqFood {
    private Long foodId;
    private Integer quantity;

}
