package com.nastyastrel.appkafkapostgres.service.consumer;

import com.nastyastrel.appkafkapostgres.model.Message;

import java.util.List;

public interface ConsumerService {
    List<Message> consume();
}
