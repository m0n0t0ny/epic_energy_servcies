package com.epicenergyservices.u5w4.config;

import com.epicenergyservices.u5w4.entities.User;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

    private String mailgunAPIKey;
    private String domainName;

    public EmailSender(@Value("${mailgun.apikey}") String mailgunAPIKey, @Value("${mailgun.domainname}") String domainName) {
        this.mailgunAPIKey = mailgunAPIKey;
        this.domainName = domainName;
    }

    public void sendRegistrationEmail(User recipient) {
        Unirest.post("https://api.mailgun.net/v3/" + domainName + "/messages")
                .basicAuth("api", mailgunAPIKey)
                .queryString("from", "Mirco orosio <mircoorosio@gmail.com>")
                .queryString("to", recipient.getEmail())
                .queryString("subject", "Registrazione completata!")
                .queryString("text", "Complimenti " + recipient.getName() + " per esserti registrato").asJson();
    }
}
