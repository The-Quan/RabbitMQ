package org.example.demorabbitmq.service;

import org.example.demorabbitmq.entities.ForgotPasswordMessage;
import org.example.demorabbitmq.entities.PasswordResetHistory;
import org.example.demorabbitmq.repositories.PasswordHistoryRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PasswordHistoryService {

    @Autowired
    private PasswordHistoryRepository repository;

    @RabbitListener(queues = "history.queue")
    public void listenSaveHistory(ForgotPasswordMessage message) {
        PasswordResetHistory history = new PasswordResetHistory();
        history.setEmail(message.getEmail());
        history.setNewPasswords(message.getNewPasswords());
        history.setResetRequestedAt(LocalDateTime.now());
        repository.save(history);
        System.out.println("PasswordHistoryService: Saved history for " + message.getEmail());
    }
}

