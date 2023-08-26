package com.example.laptopstore.services.messaging.service;

import com.example.laptopstore.entity.Laptop;
import com.example.laptopstore.services.KafkaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaMessagingService {
    private final KafkaService kafkaService;


    @Transactional
    @KafkaListener(topics = "${spring.kafka.template.default-topic}",groupId = "${spring.kafka.consumer.group-id}")
    public Laptop createOrder(Laptop laptop) {
        log.info("Message comed to kafcaListener, congratulations!!! {} !!!",
                laptop.toString());
        kafkaService.save(laptop);
        return laptop;

    }

}





