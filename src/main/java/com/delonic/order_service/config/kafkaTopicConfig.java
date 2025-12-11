package com.delonic.order_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class kafkaTopicConfig {
    @Bean
    public NewTopic OrderTopic(){
        return new NewTopic("order", 1, (short) 1);
    }
}
