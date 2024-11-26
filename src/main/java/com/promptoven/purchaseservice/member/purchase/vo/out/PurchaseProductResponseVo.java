package com.promptoven.purchaseservice.member.purchase.vo.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class PurchaseProductResponseVo {

    private Long id;

    private String purchaseUuid;

    private String productUuid;

    private String memberUuid;

    private boolean writtenReview;
}
