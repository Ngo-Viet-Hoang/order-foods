package com.example.orderfood.controller;

import com.example.orderfood.entity.Note;
import com.example.orderfood.service.FirebaseMessagingService;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class FirebaseController {
    final FirebaseMessagingService firebaseService;
    @PostMapping("/send-notification")
    @ResponseBody
    public String sendNotification(@RequestBody Note note,
                                   @RequestParam String token) throws FirebaseMessagingException {
        return firebaseService.sendNotification(note, token);
    }
}
