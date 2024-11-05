package com.promptoven.purchaseservice.global.common;

import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class UuidGenerator {

    public static String generatePurchaseUuid() {
        return "PC-" + UUID.randomUUID().toString();
    }
}