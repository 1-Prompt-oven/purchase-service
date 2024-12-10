package com.promptoven.purchaseservice.global.config;

import com.promptoven.purchaseservice.member.purchase.dto.in.RequestMessageDto;
import com.promptoven.purchaseservice.member.purchase.dto.in.RequestReviewMessageDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Value("${spring.kafka.text-bootstrap-servers}")
    private String bootstrapServers;

    // Generic ConsumerFactory 생성 메서드
    private <T> ConsumerFactory<String, T> createConsumerFactory(Class<T> valueType, String groupId) {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);

        configProps.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
        configProps.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class.getName());

        configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, valueType.getName());
        configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    // Generic ListenerContainerFactory 생성 메서드
    private <T> ConcurrentKafkaListenerContainerFactory<String, T> createKafkaListenerContainerFactory(
            ConsumerFactory<String, T> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, T> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

    // RequestMessageDto 전용 Factory 생성
    @Bean
    public ConsumerFactory<String, RequestMessageDto> requestMessageConsumerFactory() {
        return createConsumerFactory(RequestMessageDto.class, "kafka-payment-purchase-service");
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, RequestMessageDto> requestMessageKafkaListenerContainerFactory() {
        return createKafkaListenerContainerFactory(requestMessageConsumerFactory());
    }

    // RequestReviewMessageDto 전용 Factory 생성
    @Bean
    public ConsumerFactory<String, RequestReviewMessageDto> requestReviewConsumerFactory() {
        return createConsumerFactory(RequestReviewMessageDto.class, "kafka-create-review-service");
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, RequestReviewMessageDto> requestReviewKafkaListenerContainerFactory() {
        return createKafkaListenerContainerFactory(requestReviewConsumerFactory());
    }
}
