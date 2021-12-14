package com.nastyastrel.appkafkapostgres;

import com.nastyastrel.appkafkapostgres.repository.MessageFromTableRepository;
import com.nastyastrel.appkafkapostgres.repository.MessageToTableRepository;
import com.nastyastrel.appkafkapostgres.service.consumer.ConsumerService;
import com.nastyastrel.appkafkapostgres.service.producer.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class AppKafkaPostgresApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppKafkaPostgresApplication.class);
    @Value("${application.mode.producer}")
    private boolean producerMode;

    public static void main(String[] args) {
        LOGGER.info("Starting the application");
        SpringApplication.run(AppKafkaPostgresApplication.class, args);
        LOGGER.info("Finishing the application");
    }

    @Bean
    public ApplicationRunner runner(ProducerService producerService, ConsumerService consumerService, MessageFromTableRepository messageFromTableRepository, MessageToTableRepository messageToTableRepository) {
        return args -> {
            if (producerMode) {
                producerService.sendAllMessagesToTheTopic(messageFromTableRepository.findAll());
            } else
                messageToTableRepository.saveAll(consumerService.consume());
        };
    }
}
