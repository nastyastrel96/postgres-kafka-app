package com.nastyastrel.appkafkapostgres.repository;

import com.nastyastrel.appkafkapostgres.model.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcMessageFromTableRepository implements MessageFromTableRepository {
    private final JdbcTemplate jdbcTemplate;
    @Value("${application.table.from}")
    private String tableName;

    public JdbcMessageFromTableRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Message> findAll() {
        return jdbcTemplate.query("select id, name, timestamp from " + tableName, this::mapRowToMessage);
    }

    private Message mapRowToMessage(ResultSet row, int rowNum) throws SQLException {
        return new Message(
                row.getLong("id"),
                row.getString("name"),
                row.getTimestamp("timestamp").toLocalDateTime());
    }
}
