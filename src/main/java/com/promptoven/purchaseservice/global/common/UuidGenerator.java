package com.promptoven.purchaseservice.global.common;

import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class UuidGenerator {
    public static String generateProductUuid() {
        return "PR-" + UUID.randomUUID().toString();
    }

    public static String generateCategoryUuid() {
        return "CT-" + UUID.randomUUID().toString().substring(0, 8);
    }

}