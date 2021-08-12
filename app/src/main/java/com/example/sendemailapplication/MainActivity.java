package com.example.sendemailapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {
    String emailSender = "cemittesterkonto@gmail.com";
    String emailReceiver = "sturle.stavrum-tang@gmail.com";
    String password = "CTK2021!";
    String messageToSend = "Hei";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.transport.protocl", "smtp");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailSender, password);
                    }
                });

        try{
            Message message = new MimeMessage(session);

            message.setSubject("HEi mail");
            message.setContent(messageToSend, "text");

            message.setFrom(new InternetAddress(emailSender));
            InternetAddress receiver = new InternetAddress(emailReceiver);
            message.setRecipient(Message.RecipientType.TO, receiver);

            Transport.send(message);
        }
        catch (MessagingException e){
            throw new RuntimeException(e);
        }

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }

}