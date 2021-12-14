package com.nastyastrel.appkafkapostgres.repository;


import com.nastyastrel.appkafkapostgres.model.Message;

import java.util.List;

public interface MessageToTableRepository {
    void saveAll(List<Message> messages);
}
