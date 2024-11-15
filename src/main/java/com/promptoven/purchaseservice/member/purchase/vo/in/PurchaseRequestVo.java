package com.promptoven.purchaseservice.member.purchase.vo.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PurchaseRequestVo {

    private String memberUuid;

    private List<String> productUuids;

    private Long paymentId;

    @Builder
    public PurchaseRequestVo(String memberUuid, List<String> productUuids, Long paymentId) {
        this.memberUuid = memberUuid;
        this.productUuids = productUuids;
        this.paymentId = paymentId;
    }
}
