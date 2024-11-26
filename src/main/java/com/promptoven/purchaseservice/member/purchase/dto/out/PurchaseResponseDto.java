package com.promptoven.purchaseservice.member.purchase.dto.out;

import com.promptoven.purchaseservice.common.domain.Purchase;
import com.promptoven.purchaseservice.common.domain.PurchaseStatus;
import com.promptoven.purchaseservice.member.purchase.vo.out.PurchaseResponseVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class PurchaseResponseDto {

    private Long id;

    private String purchaseUuid;

    private String memberUuid;

    private Long paymentId;

    private LocalDateTime purchaseDate;

    private PurchaseStatus status;

    public static PurchaseResponseVo toVo(PurchaseResponseDto dto) {
        return PurchaseResponseVo.builder()
                .id(dto.getId())
                .purchaseUuid(dto.getPurchaseUuid())
                .memberUuid(dto.getMemberUuid())
                .paymentId(dto.getPaymentId())
                .purchaseDate(dto.getPurchaseDate())
                .status(dto.getStatus())
                .build();
    }

    public static PurchaseResponseDto fromEntity(Purchase purchase) {
        return PurchaseResponseDto.builder()
                .id(purchase.getId())
                .purchaseUuid(purchase.getPurchaseUuid())
                .memberUuid(purchase.getMemberUuid())
                .paymentId(purchase.getPaymentId())
                .purchaseDate(purchase.getPurchasedAt())
                .status(purchase.getStatus())
                .build();
    }
}
