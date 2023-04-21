package com.example.User.Config;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {
    //creating kafka topic in kafka cluster
    @Bean
    public NewTopic KafkaTopic() {
        return TopicBuilder.name("order-topic")
               .partitions(7) //if you want to use custom partition.
                //default partation is used
                .build();
    }
}