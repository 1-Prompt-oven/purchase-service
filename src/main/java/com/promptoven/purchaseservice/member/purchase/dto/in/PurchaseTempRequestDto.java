package com.promptoven.purchaseservice.member.purchase.dto.in;

import com.promptoven.purchaseservice.common.domain.PurchaseTemp;
import com.promptoven.purchaseservice.member.purchase.vo.in.PurchaseTempRequestVo;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PurchaseTempRequestDto {

    private String memberUuid;
    private String productUuid;
    private String productName;
    private Double price;

    public static PurchaseTempRequestDto toDto(PurchaseTempRequestVo vo) {
        return PurchaseTempRequestDto.builder()
                .memberUuid(vo.getMemberUuid())
                .productUuid(vo.getProductUuid())
                .productName(vo.getProductName())
                .price(vo.getPrice())
                .build();
    }

    public static PurchaseTemp toEntity(PurchaseTempRequestDto dto) {
        return PurchaseTemp.builder()
                .memberUuid(dto.getMemberUuid())
                .productUuid(dto.getProductUuid())
                .productName(dto.getProductName())
                .price(dto.getPrice())
                .build();
    }
}
