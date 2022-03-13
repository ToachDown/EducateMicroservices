package com.timmmy.cutomer;

public record CustomerRegistrationRequest(
        String firstname,
        String lastname,
        String email) {
}
