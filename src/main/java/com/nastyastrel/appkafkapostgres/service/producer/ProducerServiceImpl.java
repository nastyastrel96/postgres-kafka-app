package com.nastyastrel.appkafkapostgres.service.producer;

import com.nastyastrel.appkafkapostgres.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProducerServiceImpl implements ProducerService {
    @Value("${spring.kafka.properties.topic.name}")
    private String topicName;
    private final KafkaTemplate<String, Message> kafkaTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerServiceImpl.class);

    public ProducerServiceImpl(KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendAllMessagesToTheTopic(List<Message> messages) {
        for (Message message : messages) {
            kafkaTemplate.send(topicName, message);
            LOGGER.info("Записали в топик " + message);
        }
    }
}
