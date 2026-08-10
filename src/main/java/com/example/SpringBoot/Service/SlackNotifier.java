package com.example.SpringBoot.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class SlackNotifier {

    @Value("${slack.webhook.url}")
    private String webhookUrl;
    private final RestClient restClient = RestClient.create();

    public void send(String message){
        try{
            restClient.post()
                    .uri(webhookUrl)
                    .body(Map.of("text" , message))
                    .retrieve()
                    .toBodilessEntity();
        }catch (Exception e){
            System.err.println("Failed to send Slack notification: " + e.getMessage());
        }
    }
}
