package com.promptoven.purchaseservice.member.purchase.dto.in;

import com.promptoven.purchaseservice.common.domain.Purchase;
import com.promptoven.purchaseservice.common.domain.PurchaseProduct;
import com.promptoven.purchaseservice.common.domain.PurchaseStatus;
import com.promptoven.purchaseservice.global.common.UuidGenerator;
import com.promptoven.purchaseservice.member.purchase.vo.in.PurchaseCartRequestVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
public class PurchaseCartRequestDto {

    private String memberUuid;

    private List<String> productUuids;

    private List<Long> cartIds;

    public static PurchaseCartRequestDto toDto(PurchaseCartRequestVo vo) {
        return PurchaseCartRequestDto.builder()
                .memberUuid(vo.getMemberUuid())
                .productUuids(vo.getProductUuids())
                .cartIds(vo.getCartIds())
                .build();
    }

    public static Purchase toPurchaseEntity(PurchaseCartRequestDto dto) {
        return Purchase.builder()
                .purchaseUuid(UuidGenerator.generatePurchaseUuid())
                .memberUuid(dto.getMemberUuid())
                .purchasedAt(LocalDateTime.now())
                .status(PurchaseStatus.PENDING)
                .build();
    }

    public List<PurchaseProduct> toPurchaseProductEntities(String purchaseUuid) {
        return productUuids.stream()
                .map(productUuid -> PurchaseProduct.builder()
                        .purchaseUuid(purchaseUuid)
                        .productUuid(productUuid)
                        .writtenReview(false)
                        .build())
                .collect(Collectors.toList());
    }
}
