package com.nastyastrel.appkafkapostgres.repository;

import com.nastyastrel.appkafkapostgres.model.Message;

import java.util.List;

public interface MessageFromTableRepository {
    List<Message> findAll();
}
