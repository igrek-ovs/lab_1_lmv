package com.example.hospital.password;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.util.Random;
public class PasswordGenerator {

    // Метод для генерации случайного пароля из 10 символов
    public static String generatePassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    // Метод для отправки пароля на указанную почту
    public static void sendEmail(String toEmail, String password) throws MessagingException {
        String fromEmail = "admenigrekahaha@gmail.com"; // Адрес отправителя
        String passwordEmail = "vdsfdylalpwhupxa"; // Пароль от почты отправителя

        // Настройки SMTP-сервера
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Создание сессии для отправки почты
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, passwordEmail);
            }
        });

        // Создание сообщения
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        // Задание темы сообщения
        message.setSubject("Ваш новый пароль");

        // Задание тела сообщения
        message.setText("Ваш новый пароль: " + password);

        // Отправка сообщения
        Transport.send(message);
    }
    public static void sendLetter(String toEmail, String letter) throws MessagingException {
        String fromEmail = "admenigrekahaha@gmail.com"; // Адрес отправителя
        String passwordEmail = "vdsfdylalpwhupxa"; // Пароль от почты отправителя

        // Настройки SMTP-сервера
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Создание сессии для отправки почты
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, passwordEmail);
            }
        });

        // Создание сообщения
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        // Задание темы сообщения
        message.setSubject("Вам письмо:  ");

        // Задание тела сообщения
        message.setText("Ваше письмо:\n\n " + letter);

        // Отправка сообщения
        Transport.send(message);
    }
}
