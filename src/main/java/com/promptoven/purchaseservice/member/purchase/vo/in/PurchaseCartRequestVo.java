package com.promptoven.purchaseservice.member.purchase.vo.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PurchaseCartRequestVo {

    private String memberUuid;

    private List<String> productUuids;

    private List<Long> cartIds;

    @Builder
    public PurchaseCartRequestVo(String memberUuid, List<String> productUuids, List<Long> cartIds) {
        this.memberUuid = memberUuid;
        this.productUuids = productUuids;
        this.cartIds = cartIds;
    }
}
