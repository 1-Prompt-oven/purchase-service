package com.promptoven.purchaseservice.member.purchase.application;

import com.promptoven.purchaseservice.common.domain.Purchase;
import com.promptoven.purchaseservice.member.purchase.dto.in.PurchaseCartRequestDto;
import com.promptoven.purchaseservice.member.purchase.dto.in.PurchaseRequestDto;
import com.promptoven.purchaseservice.member.purchase.infrastructure.PurchaseProductRepository;
import com.promptoven.purchaseservice.member.purchase.infrastructure.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseProductRepository purchaseProductRepository;

    @Override
    public void createPurchase(PurchaseRequestDto purchaseRequestDto) {
        Purchase purchase = purchaseRepository.save(PurchaseRequestDto.toPurchaseEntity(purchaseRequestDto));
        purchaseProductRepository.saveAll(purchaseRequestDto.toPurchaseProductEntities(purchase.getPurchaseUuid()));
    }

    //  TODO : 장바구니 서비스에 Kafka 처리
    @Override
    public void createCartPurchase(PurchaseCartRequestDto purchaseCartRequestDto) {
        Purchase purchase = purchaseRepository.save(PurchaseCartRequestDto.toPurchaseEntity(purchaseCartRequestDto));
        purchaseProductRepository.saveAll(purchaseCartRequestDto.toPurchaseProductEntities(purchase.getPurchaseUuid()));
    }
}
