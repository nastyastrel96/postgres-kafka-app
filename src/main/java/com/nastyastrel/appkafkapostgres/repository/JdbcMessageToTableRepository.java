package com.nastyastrel.appkafkapostgres.repository;

import com.nastyastrel.appkafkapostgres.model.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class JdbcMessageToTableRepository implements MessageToTableRepository {
    private final JdbcTemplate jdbcTemplate;
    @Value("${application.table.to}")
    private String tableName;

    public JdbcMessageToTableRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveAll(List<Message> messages) {
        for (Message user : messages) {
            jdbcTemplate.update(
                    "insert into " + tableName + "(id, name, timestamp) values (?, ?, ?)",
                    user.id(),
                    user.name(),
                    user.localDateTime());
        }
    }
}
