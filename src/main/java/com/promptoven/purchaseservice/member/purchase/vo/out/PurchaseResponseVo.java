package com.promptoven.purchaseservice.member.purchase.vo.out;

import com.promptoven.purchaseservice.common.domain.PurchaseStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class PurchaseResponseVo {

    private final Long id;

    private final String purchaseUuid;

    private final String memberUuid;

    private final Long paymentId;

    private final LocalDateTime purchaseDate;

    private final PurchaseStatus status;
}
