package com.promptoven.purchaseservice.member.purchase.infrastructure;

import com.promptoven.purchaseservice.common.domain.PurchaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseProductRepository extends JpaRepository<PurchaseProduct, Long> {

    List<PurchaseProduct> findAllByPurchaseUuidIn(List<String> purchaseUuids);
}
