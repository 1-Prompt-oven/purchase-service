package com.promptoven.purchaseservice.member.purchase.dto.out;

import com.promptoven.purchaseservice.common.domain.PurchaseTemp;
import com.promptoven.purchaseservice.member.purchase.vo.out.PurchaseTempResponseVo;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PurchaseTempResponseDto {

    private String memberUuid;
    private String productUuid;
    private String productName;
    private Double price;

    public static PurchaseTempResponseVo toVo(PurchaseTempResponseDto dto) {
        return PurchaseTempResponseVo.builder()
                .memberUuid(dto.getMemberUuid())
                .productUuid(dto.getProductUuid())
                .productName(dto.getProductName())
                .price(dto.getPrice())
                .build();
    }

    public static PurchaseTempResponseDto fromEntity(PurchaseTemp purchaseTemp) {
        return PurchaseTempResponseDto.builder()
                .memberUuid(purchaseTemp.getMemberUuid())
                .productUuid(purchaseTemp.getProductUuid())
                .productName(purchaseTemp.getProductName())
                .price(purchaseTemp.getPrice())
                .build();
    }
}
