package com.nastyastrel.appkafkapostgres.service.producer;

import com.nastyastrel.appkafkapostgres.model.Message;

import java.util.List;

public interface ProducerService {
    void sendAllMessagesToTheTopic(List<Message> messages);
}
