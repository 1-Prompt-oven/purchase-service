package com.promptoven.purchaseservice.member.purchase.infrastructure;

import com.promptoven.purchaseservice.common.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Purchase> findAllByMemberUuid(String memberUuid);
}
