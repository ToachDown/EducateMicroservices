package com.timmy.notification;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("api/v1/notify")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public void sendNotification (@RequestBody CustomerNotifyRequest request) {
        log.info("send notification to {}", request);
        notificationService.sendNotification(request);
    }
}
