package com.promptoven.purchaseservice.member.purchase.vo.out;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PurchaseProductResponseVo {

    private final Long id;

    private final String purchaseUuid;

    private final String productUuid;

    private final String memberUuid;

    private final boolean writtenReview;
}
