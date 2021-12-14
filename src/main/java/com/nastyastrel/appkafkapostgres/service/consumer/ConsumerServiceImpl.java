package com.nastyastrel.appkafkapostgres.service.consumer;

import com.nastyastrel.appkafkapostgres.model.Message;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Value("${spring.kafka.properties.topic.name}")
    private String topicName;
    @Value("${spring.kafka.consumer.group-id}")
    private String groupName;
    private final ConsumerFactory<String, Message> consumerFactory;
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerServiceImpl.class);

    public ConsumerServiceImpl(ConsumerFactory<String, Message> consumerFactory) {
        this.consumerFactory = consumerFactory;
    }

    @Override
    public List<Message> consume() {
        List<Message> messages = new ArrayList<>();
        try (Consumer<String, Message> consumer = consumerFactory.createConsumer(groupName, null)) {
            List<PartitionInfo> partitionsInfo = consumer.partitionsFor(topicName);
            List<TopicPartition> topicPartitionList = new ArrayList<>();
            partitionsInfo.forEach(partitionInfo -> topicPartitionList.add(new TopicPartition(topicName, partitionInfo.partition())));
            consumer.assign(topicPartitionList);
            while (true) {
                ConsumerRecords<String, Message> consumerRecords = consumer.poll(Duration.ofSeconds(1));
                if (consumerRecords.isEmpty()) {
                    break;
                }
                consumerRecords.forEach(consumerRecord -> {
                    LOGGER.info("Received from topic: " + consumerRecord.value().toString());
                    messages.add(consumerRecord.value());
                });
            }
        }
        return messages;
    }
}
