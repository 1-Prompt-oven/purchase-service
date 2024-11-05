package com.promptoven.purchaseservice.member.purchase.dto.in;

import com.promptoven.purchaseservice.common.domain.Purchase;
import com.promptoven.purchaseservice.common.domain.PurchaseProduct;
import com.promptoven.purchaseservice.common.domain.PurchaseStatus;
import com.promptoven.purchaseservice.global.common.UuidGenerator;
import com.promptoven.purchaseservice.member.purchase.vo.in.PurchaseRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class PurchaseRequestDto {

    private String memberUuid;

    private List<String> productUuids;

    @Builder
    public PurchaseRequestDto(String memberUuid, List<String> productUuids) {
        this.memberUuid = memberUuid;
        this.productUuids = productUuids;
    }

    public static PurchaseRequestDto toDto(PurchaseRequestVo vo) {
        return PurchaseRequestDto.builder()
                .memberUuid(vo.getMemberUuid())
                .productUuids(vo.getProductUuids())
                .build();
    }

    public static Purchase toPurchaseEntity(PurchaseRequestDto dto) {
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
