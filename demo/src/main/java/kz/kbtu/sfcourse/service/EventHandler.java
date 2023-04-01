package com.example.demo.service;

import com.example.demo.domain.event.AccountCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventHandler {

    //private final KafkaTemplate<String, String> kafkaTemplate;
    //private final NewTopic topic1;

    @EventListener
    public void process(AccountCreatedEvent event) {
        log.info("event received: " + event);
        //kafkaTemplate.send("test_topic", event.getAggregateObjectId(),"test");
    }
}
