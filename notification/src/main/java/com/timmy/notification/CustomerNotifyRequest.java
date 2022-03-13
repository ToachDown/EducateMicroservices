package com.timmy.notification;

public record CustomerNotifyRequest(
        String message,
        String firstname,
        String lastname,
        String email) {
}
