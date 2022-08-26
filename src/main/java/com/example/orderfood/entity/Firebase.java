package com.example.orderfood.entity;

import lombok.Data;

import java.util.Map;

public class Firebase {
    @Data
    public class Note {
        private String subject;
        private String content;
        private Map<String, String> data;
        private String image;
    }
}
