# Kafka-Postgres-Application

Kafka-Postgres-App is Java Spring boot application, designed to use Apache Kafka as a message broker to fetch data from one database and send it to another one. This application works in two different modes: 

- the first mode makes it possible to fetch data from one database and send it to the message broker's topic in JSON, 

- in the second mode the consumer get it in JSON from the topic and save to the second database. 
 

## Build
```
mvn clean install
```

## Run
### Prerequisites
- Java 11 + Maven
- Apache Kafka
- Postgres database

### Relevant properties
This application uses Postgres database,
```
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres?ssl=false&serverTimezone=UTC
spring.datasource.username=postgres
spring.datasource.password=mysecretpassword
spring.datasource.driver-class-name=org.postgresql.Driver
```
with two different tables to fetch data from and save to:
```
application.table.from=kafka_from
application.table.to=kafka_to
```
To be able to run app in two different modes we change this property's value (true/false)
```
application.mode.producer=true
```
Bootstrap server and broker's topic are
```
spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.properties.topic.name=demo-topic
```
### Application' modes

There are two modes of application work: 
- the first mode makes it possible to fetch data from one Data Base and send it to the message broker's topic in JSON by producer

In order to launch it in the first "producer mode", you need

```
java -jar app-kafka-postgres-0.0.1-SNAPSHOT.jar --application.mode.producer=true
```
 - the second mode makes it possible to fetch data from the message broker's topic in JSON by consumer and save in the other Data Base
In order to launch it in the second "consumer mode", you need

```
java -jar app-kafka-postgres-0.0.1-SNAPSHOT.jar --application.mode.producer=false
```

