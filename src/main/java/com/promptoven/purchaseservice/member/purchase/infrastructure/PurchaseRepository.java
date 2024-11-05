package com.promptoven.purchaseservice.member.purchase.infrastructure;

import com.promptoven.purchaseservice.common.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
