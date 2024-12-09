package com.promptoven.purchaseservice.member.purchase.application;

import com.promptoven.purchaseservice.common.domain.Purchase;
import com.promptoven.purchaseservice.member.purchase.dto.in.RequestMessageDto;
import com.promptoven.purchaseservice.member.purchase.dto.in.RequestReviewMessageDto;
import com.promptoven.purchaseservice.member.purchase.infrastructure.PurchaseProductRepository;
import com.promptoven.purchaseservice.member.purchase.infrastructure.PurchaseRepository;
import com.promptoven.purchaseservice.member.purchase.infrastructure.PurchaseTempRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseProductRepository purchaseProductRepository;
    private final PurchaseTempRepository purchaseTempRepository;

    @KafkaListener(topics = "${payment-create-event}", groupId = "kafka-payment-purchase-service")
    public void consumeCreate(RequestMessageDto message) {

        log.info("consumeCreate: {}", message);

        Purchase purchase = purchaseRepository.save(message.toPurchaseEntity(message.getMemberUuid(), message.getPaymentId()));

        purchaseTempRepository.deleteAllByMemberUuid(message.getMemberUuid());

        message.getProductUuids().forEach(purchaseProduct -> {
            purchaseProductRepository.save(RequestMessageDto.toPurchaseProductEntity(purchase.getPurchaseUuid(), purchaseProduct, purchase.getMemberUuid()));
        });
    }

    @KafkaListener(topics = "${review-create-event}", groupId = "kafka-create-review-service")
    public void consumeReviewCreate(RequestReviewMessageDto message) {
        purchaseProductRepository.findByMemberUuidAndProductUuid(
                message.getAuthorUuid(), message.getProductUuid()
        ).ifPresent(purchaseProduct -> {
            if (!purchaseProduct.isWrittenReview()) {
                purchaseProduct.setWrittenReview(true);
                purchaseProductRepository.save(purchaseProduct);
            }
        });
    }

    @KafkaListener(topics = "${review-delete-event}", groupId = "kafka-delete-review-service")
    public void consumeReviewDelete(RequestReviewMessageDto message) {
        purchaseProductRepository.findByMemberUuidAndProductUuid(
                message.getAuthorUuid(), message.getProductUuid()
        ).ifPresent(purchaseProduct -> {
            if (purchaseProduct.isWrittenReview()) {
                purchaseProduct.setWrittenReview(false);
                purchaseProductRepository.save(purchaseProduct);
            }
        });
    }
}
