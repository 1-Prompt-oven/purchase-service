package com.promptoven.purchaseservice.member.purchase.dto.in;

import com.promptoven.purchaseservice.common.domain.Purchase;
import com.promptoven.purchaseservice.common.domain.PurchaseProduct;
import com.promptoven.purchaseservice.common.domain.PurchaseStatus;
import com.promptoven.purchaseservice.global.common.UuidGenerator;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestMessageDto {

    private Long paymentId;

    private String memberUuid;

    private List<String> productUuids;

    public Purchase toPurchaseEntity(String memberUuid, Long paymentId) {
        return Purchase.builder()
                .purchaseUuid(UuidGenerator.generatePurchaseUuid())
                .memberUuid(memberUuid)
                .paymentId(paymentId)
                .purchasedAt(LocalDateTime.now())
                .status(PurchaseStatus.COMPLETED)
                .build();
    }

    public static PurchaseProduct toPurchaseProductEntity(String purchaseUuid, String productUuid) {
        return PurchaseProduct.builder()
                .purchaseUuid(purchaseUuid)
                .productUuid(productUuid)
                .writtenReview(false)
                .build();
    }

}
