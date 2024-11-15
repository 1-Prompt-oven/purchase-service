package com.promptoven.purchaseservice.member.purchase.application;

import com.promptoven.purchaseservice.common.domain.Purchase;
import com.promptoven.purchaseservice.member.purchase.dto.in.PurchaseCartRequestDto;
import com.promptoven.purchaseservice.member.purchase.dto.in.PurchaseRequestDto;
import com.promptoven.purchaseservice.member.purchase.dto.out.PurchaseProductResponseDto;
import com.promptoven.purchaseservice.member.purchase.infrastructure.PurchaseProductRepository;
import com.promptoven.purchaseservice.member.purchase.infrastructure.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public void createCartPurchase(PurchaseCartRequestDto purchaseCartRequestDto) {

        Purchase purchase = purchaseRepository.save(PurchaseCartRequestDto.toPurchaseEntity(purchaseCartRequestDto));

        purchaseProductRepository.saveAll(purchaseCartRequestDto.toPurchaseProductEntities(purchase.getPurchaseUuid()));
    }

    @Override
    public List<PurchaseProductResponseDto> getPurchaseProduct(String memberUuid) {

        // Step 1: memberUuid로 purchases를 조회하여 purchaseUuid 리스트를 가져옴
        List<String> purchaseUuids = purchaseRepository.findAllByMemberUuid(memberUuid).stream()
                .map(Purchase::getPurchaseUuid)
                .collect(Collectors.toList());

        // Step 2: purchaseUuid 리스트로 purchaseProducts 조회
        return purchaseProductRepository.findAllByPurchaseUuidIn(purchaseUuids).stream()
                .map(PurchaseProductResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

}
