package com.example.orderfood.entity.reqBody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReqOrder {

    private List<ReqFood> foods;
    private String fullName;
    private String note;
    private String phone;
    private String mealTime;



}
