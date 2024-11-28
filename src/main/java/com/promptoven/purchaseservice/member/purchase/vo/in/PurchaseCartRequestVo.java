package com.promptoven.purchaseservice.member.purchase.vo.in;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class PurchaseCartRequestVo {

    private final String memberUuid;

    private final List<String> productUuids;

    private final List<Long> cartIds;
}
