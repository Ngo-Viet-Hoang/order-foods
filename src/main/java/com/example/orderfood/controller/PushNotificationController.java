//package com.example.orderfood.controller;
//
//import com.example.orderfood.entity.PushNotificationRequest;
//import com.example.orderfood.entity.PushNotificationResponse;
//import com.example.orderfood.service.PushNotificationService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//@Controller
//public class PushNotificationController {
//    private PushNotificationService pushNotificationService;
//
//    public PushNotificationController(PushNotificationService pushNotificationService) {
//        this.pushNotificationService = pushNotificationService;
//    }
//
//    @PostMapping("/notification/token")
//    public ResponseEntity sendTokenNotification(@RequestBody PushNotificationRequest request) {
//        pushNotificationService.sendPushNotificationToToken(request);
//        System.out.println("princr");
//        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
//    }
//}
