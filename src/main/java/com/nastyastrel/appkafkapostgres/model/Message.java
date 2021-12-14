package com.nastyastrel.appkafkapostgres.model;

import java.time.LocalDateTime;

public record Message(Long id, String name, LocalDateTime localDateTime) {
}
