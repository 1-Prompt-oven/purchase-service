package com.promptoven.purchaseservice.member.purchase.application;

import com.promptoven.purchaseservice.common.domain.Purchase;
import com.promptoven.purchaseservice.global.common.CursorPage;
import com.promptoven.purchaseservice.member.purchase.dto.in.PurchaseCartRequestDto;
import com.promptoven.purchaseservice.member.purchase.dto.in.PurchaseRequestDto;
import com.promptoven.purchaseservice.member.purchase.dto.out.PurchaseProductResponseDto;
import com.promptoven.purchaseservice.member.purchase.dto.out.PurchaseResponseDto;
import com.promptoven.purchaseservice.member.purchase.infrastructure.PurchaseProductRepository;
import com.promptoven.purchaseservice.member.purchase.infrastructure.PurchaseRepository;
import com.promptoven.purchaseservice.member.purchase.infrastructure.PurchaseRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseProductRepository purchaseProductRepository;
    private final PurchaseRepositoryCustom purchaseRepositoryCustom;

    @Transactional
    @Override
    public void createPurchase(PurchaseRequestDto purchaseRequestDto) {

        Purchase purchase = purchaseRepository.save(PurchaseRequestDto.toPurchaseEntity(purchaseRequestDto));

        purchaseProductRepository.saveAll(purchaseRequestDto.toPurchaseProductEntities(purchase.getPurchaseUuid()));
    }

    @Transactional
    @Override
    public void createCartPurchase(PurchaseCartRequestDto purchaseCartRequestDto) {

        Purchase purchase = purchaseRepository.save(PurchaseCartRequestDto.toPurchaseEntity(purchaseCartRequestDto));

        purchaseProductRepository.saveAll(purchaseCartRequestDto.toPurchaseProductEntities(purchase.getPurchaseUuid()));
    }

    @Transactional(readOnly = true)
    @Override
    public CursorPage<PurchaseResponseDto> getPurchaseList(String memberUuid, Long lastPurchaseId, Integer pageSize) {
        return purchaseRepositoryCustom.getPurchaseListWithPagination(memberUuid, lastPurchaseId, pageSize);
    }

    @Transactional(readOnly = true)
    @Override
    public CursorPage<PurchaseProductResponseDto> getPurchaseProduct(String memberUuid, Long lastProductId, Integer pageSize) {
        return purchaseRepositoryCustom.getPurchaseProductWithPagination(memberUuid, lastProductId, pageSize);
    }

    @Transactional(readOnly = true)
    @Override
    public List<PurchaseProductResponseDto> getPurchaseProductByPurchaseUuid(String purchaseUuid) {
        return purchaseProductRepository.findByPurchaseUuid(purchaseUuid).stream()
                .map(PurchaseProductResponseDto::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public PurchaseResponseDto getPurchaseByPurchaseUuid(String purchaseUuid) {
        return PurchaseResponseDto.fromEntity(purchaseRepository.findByPurchaseUuid(purchaseUuid).orElseThrow());
    }
}
