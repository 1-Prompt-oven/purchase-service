package com.promptoven.purchaseservice.member.purchase.vo.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class PurchaseProductResponseVo {
    
    private String purchaseUuid;

    private String productUuid;

    private boolean writtenReview;
}
