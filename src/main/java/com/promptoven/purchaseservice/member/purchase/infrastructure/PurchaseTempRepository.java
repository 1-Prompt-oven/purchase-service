package com.promptoven.purchaseservice.member.purchase.infrastructure;

import com.promptoven.purchaseservice.common.domain.PurchaseTemp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseTempRepository extends JpaRepository<PurchaseTemp, Long> {

    List<PurchaseTemp> findByMemberUuid(String memberUuid);

    void deleteAllByMemberUuid(String memberUuid);
}
