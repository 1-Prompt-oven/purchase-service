package com.promptoven.purchaseservice.member.purchase.infrastructure;

import com.promptoven.purchaseservice.global.common.CursorPage;
import com.promptoven.purchaseservice.member.purchase.dto.out.PurchaseProductResponseDto;
import com.promptoven.purchaseservice.member.purchase.dto.out.PurchaseResponseDto;

public interface PurchaseRepositoryCustom {

    CursorPage<PurchaseResponseDto> getPurchaseListWithPagination(String memberUuid, Long lastPurchaseId, Integer pageSize);

    CursorPage<PurchaseProductResponseDto> getPurchaseProductWithPagination(String memberUuid, Long lastProductId, Integer pageSize);
}
