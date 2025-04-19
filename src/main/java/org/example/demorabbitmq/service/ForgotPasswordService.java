package org.example.demorabbitmq.service;

import org.example.demorabbitmq.entities.ForgotPasswordMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ForgotPasswordService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;


    public void forgotPassword(String userEmail, String newPasswords) {
        ForgotPasswordMessage message = new ForgotPasswordMessage(userEmail, newPasswords);
        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }
}
