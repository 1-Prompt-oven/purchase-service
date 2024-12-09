package com.promptoven.purchaseservice.member.purchase.vo.in;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PurchaseTempRequestVo {

    private final String memberUuid;
    private final String productUuid;
    private final String productName;
    private final Double price;
}
