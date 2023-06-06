package com.demo.bicyclestore.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
@Slf4j
public class KafkaClient {
    @Value("${kafka.topic.name}")
    private String topicName;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    public void sendMessage(String msg) throws ExecutionException, InterruptedException {
        var response = kafkaTemplate.send(topicName, msg);
        log.debug("trace written in kafka. {}", response.get().toString());
    }
}
