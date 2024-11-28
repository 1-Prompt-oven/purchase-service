package com.promptoven.purchaseservice.member.purchase.infrastructure;

import com.promptoven.purchaseservice.common.domain.PurchaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PurchaseProductRepository extends JpaRepository<PurchaseProduct, Long> {

    Optional<PurchaseProduct> findByMemberUuidAndProductUuid(String memberUuid, String productUuid);
}
