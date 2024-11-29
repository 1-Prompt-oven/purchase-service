package com.promptoven.purchaseservice.member.purchase.application;

import com.promptoven.purchaseservice.global.common.CursorPage;
import com.promptoven.purchaseservice.member.purchase.dto.in.PurchaseCartRequestDto;
import com.promptoven.purchaseservice.member.purchase.dto.in.PurchaseRequestDto;
import com.promptoven.purchaseservice.member.purchase.dto.out.PurchaseProductResponseDto;
import com.promptoven.purchaseservice.member.purchase.dto.out.PurchaseResponseDto;

import java.util.List;

public interface PurchaseService {

    void createPurchase(PurchaseRequestDto purchaseRequestDto);

    void createCartPurchase(PurchaseCartRequestDto purchaseCartRequestDto);

    CursorPage<PurchaseResponseDto> getPurchaseList(String memberUuid, Long lastPurchaseId, Integer pageSize);

    CursorPage<PurchaseProductResponseDto> getPurchaseProduct(String memberUuid, Long lastProductId, Integer pageSize);

    List<PurchaseProductResponseDto> getPurchaseProductByPurchaseUuid(String purchaseUuid);
}
