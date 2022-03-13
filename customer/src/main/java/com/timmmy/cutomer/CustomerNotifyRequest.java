package com.timmmy.cutomer;

public record CustomerNotifyRequest(
        String message,
        String firstname,
        String lastname,
        String email) {
}
