package com.promptoven.purchaseservice.member.purchase.application;

import com.promptoven.purchaseservice.common.domain.Purchase;
import com.promptoven.purchaseservice.member.purchase.dto.in.RequestMessageDto;
import com.promptoven.purchaseservice.member.purchase.infrastructure.PurchaseProductRepository;
import com.promptoven.purchaseservice.member.purchase.infrastructure.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private static final String CREATE_TOPIC = "create_payment_event";
    private final PurchaseRepository purchaseRepository;
    private final PurchaseProductRepository purchaseProductRepository;

    @KafkaListener(topics = CREATE_TOPIC, groupId = "kafka-payment-purchase-service")
    public void consumeCreate(RequestMessageDto message) {

        log.info("consumeCreate: {}", message);

        Purchase purchase = purchaseRepository.save(message.toPurchaseEntity(message.getMemberUuid(), message.getPaymentId()));

        message.getProductUuid().forEach(purchaseProduct -> {
            purchaseProductRepository.save(RequestMessageDto.toPurchaseProductEntity(purchase.getPurchaseUuid(), purchaseProduct));
        });
    }
}
