package com.pushnotification.pushnotification;

import io.github.jav.exposerversdk.PushClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NotificationController {

    @Autowired
    private ExpoNotificationService expoNotificationService;

    @PostMapping("/sendNotification")
    public ResponseEntity<String> sendNotification(
                                                   @RequestParam String title,
                                                   @RequestParam String body) {


        List<String> supplierNames = Arrays.asList("ExponentPushToken[]" , "ExponentPushToken[]");

        supplierNames.stream().forEach((e)->
                {
                    try {
                        expoNotificationService.sendExpoNotification(e, title, body);

                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    } catch (PushClientException ex) {
                        ex.printStackTrace();
                    }

                }
        );
        return ResponseEntity.ok("Notification sent successfully");

    }
}
