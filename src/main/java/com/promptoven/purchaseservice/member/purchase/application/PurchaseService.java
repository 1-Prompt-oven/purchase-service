package com.promptoven.purchaseservice.member.purchase.application;

import com.promptoven.purchaseservice.member.purchase.dto.in.PurchaseCartRequestDto;
import com.promptoven.purchaseservice.member.purchase.dto.in.PurchaseRequestDto;

public interface PurchaseService {

    void createPurchase(PurchaseRequestDto purchaseRequestDto);

    void createCartPurchase(PurchaseCartRequestDto purchaseCartRequestDto);
}
