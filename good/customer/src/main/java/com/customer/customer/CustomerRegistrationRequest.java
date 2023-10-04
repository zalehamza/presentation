package com.customer.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}
