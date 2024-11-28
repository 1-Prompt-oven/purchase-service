package com.promptoven.purchaseservice.member.purchase.dto.out;

import com.promptoven.purchaseservice.common.domain.PurchaseProduct;
import com.promptoven.purchaseservice.member.purchase.vo.out.PurchaseProductResponseVo;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PurchaseProductResponseDto {

    private Long id;

    private String purchaseUuid;

    private String productUuid;

    private String memberUuid;

    private boolean writtenReview;

    public static PurchaseProductResponseVo toVo(PurchaseProductResponseDto dto) {
        return PurchaseProductResponseVo.builder()
                .id(dto.getId())
                .purchaseUuid(dto.getPurchaseUuid())
                .productUuid(dto.getProductUuid())
                .memberUuid(dto.getMemberUuid())
                .writtenReview(dto.isWrittenReview())
                .build();
    }

    public static PurchaseProductResponseDto fromEntity(PurchaseProduct purchaseProduct) {
        return PurchaseProductResponseDto.builder()
                .id(purchaseProduct.getId())
                .purchaseUuid(purchaseProduct.getPurchaseUuid())
                .productUuid(purchaseProduct.getProductUuid())
                .memberUuid(purchaseProduct.getMemberUuid())
                .writtenReview(purchaseProduct.isWrittenReview())
                .build();
    }
}
