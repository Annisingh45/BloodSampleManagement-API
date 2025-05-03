package com.ketan.bsm.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public void sendMail(String to,String subject,String text) throws MessagingException {
        log.info("Sending donation request to {}", to);
        MimeMessage message=javaMailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message,true);
        helper.setFrom("ketanpavadshetty@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text,true);
        javaMailSender.send(message);

    }

    public String generateContent(String templateName, Map<String,Object> variables){
        Context context=new Context();
        context.setVariables(variables);
        return templateEngine.process(templateName,context);
    }
}
