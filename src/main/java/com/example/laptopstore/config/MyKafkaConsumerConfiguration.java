package com.example.laptopstore.config;

import com.example.laptopstore.entity.Laptop;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class MyKafkaConsumerConfiguration {

    @Value("${spring.kafka.template.default-topic}")
    private String topicName;
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapService;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupID;


    @Bean
    ConcurrentKafkaListenerContainerFactory<String, Laptop>
    kafkaListenerContainerFactory(ConsumerFactory<String, Laptop> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String,Laptop> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

        @Bean
        ConsumerFactory<String, Laptop> consumerFactory () {
            Map<String, Object> properties = new HashMap<>();
            properties.put("bootstrap.servers", bootstrapService);
            properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupID);
            return new DefaultKafkaConsumerFactory<>(properties, new StringDeserializer(),
                    new JsonDeserializer<>(Laptop.class));


    }
}
