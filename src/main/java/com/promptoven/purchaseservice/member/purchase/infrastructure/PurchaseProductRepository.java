package com.promptoven.purchaseservice.member.purchase.infrastructure;

import com.promptoven.purchaseservice.common.domain.PurchaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseProductRepository extends JpaRepository<PurchaseProduct, Long> {
}
