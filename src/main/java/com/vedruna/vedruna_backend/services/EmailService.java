package com.vedruna.vedruna_backend.services;



public interface EmailService {
    void sendEmail(String to, String subject, String body);
}