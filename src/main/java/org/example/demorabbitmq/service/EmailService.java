package org.example.demorabbitmq.service;

import org.example.demorabbitmq.entities.ForgotPasswordMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private  JavaMailSender mailSender;

    @RabbitListener(queues = "email.queue")
    public void listenResetEmail(ForgotPasswordMessage message) {
        System.out.println("EmailService: Sending email to " + message.getEmail());

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(message.getEmail());
        mail.setSubject("Reset your password");
        mail.setText("Xin chào,\n\n"
                + "Chúng tôi đã nhận được yêu cầu đặt lại mật khẩu cho tài khoản của bạn.\n"
                + "Mật khẩu mới của bạn là:\n\n"
                + message.getNewPasswords() + "\n\n"
                + "Vui lòng đăng nhập bằng mật khẩu này và thay đổi mật khẩu ngay sau đó để đảm bảo an toàn.\n\n"
                + "Nếu bạn không thực hiện yêu cầu này, vui lòng bỏ qua email này.\n\n"
                + "Trân trọng,\nĐội ngũ hỗ trợ.");

        mailSender.send(mail);
    }
}