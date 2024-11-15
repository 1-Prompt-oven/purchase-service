package com.promptoven.purchaseservice.member.purchase.application;

import com.promptoven.purchaseservice.member.purchase.dto.in.PurchaseCartRequestDto;
import com.promptoven.purchaseservice.member.purchase.dto.in.PurchaseRequestDto;
import com.promptoven.purchaseservice.member.purchase.dto.out.PurchaseProductResponseDto;

import java.util.List;

public interface PurchaseService {

    void createPurchase(PurchaseRequestDto purchaseRequestDto);

    void createCartPurchase(PurchaseCartRequestDto purchaseCartRequestDto);

    List<PurchaseProductResponseDto> getPurchaseProduct(String memberUuid);
}
