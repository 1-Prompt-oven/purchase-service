package com.promptoven.purchaseservice.member.purchase.dto.out;

import com.promptoven.purchaseservice.common.domain.PurchaseProduct;
import com.promptoven.purchaseservice.member.purchase.vo.out.PurchaseProductResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class PurchaseProductResponseDto {

    private String purchaseUuid;

    private String productUuid;

    private boolean writtenReview;

    public static PurchaseProductResponseVo toVo(PurchaseProductResponseDto dto) {
        return PurchaseProductResponseVo.builder()
                .purchaseUuid(dto.getPurchaseUuid())
                .productUuid(dto.getProductUuid())
                .writtenReview(dto.isWrittenReview())
                .build();
    }

    public static PurchaseProductResponseDto fromEntity(PurchaseProduct purchaseProduct) {
        return PurchaseProductResponseDto.builder()
                .purchaseUuid(purchaseProduct.getPurchaseUuid())
                .productUuid(purchaseProduct.getProductUuid())
                .writtenReview(purchaseProduct.isWrittenReview())
                .build();
    }
}
