package com.promptoven.purchaseservice.common.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "purchase", indexes = {
        @Index(name = "idx_purchase_member_id_pagination", columnList = "memberUuid, id DESC"),
        @Index(name = "idx_purchase_uuid", columnList = "purchaseUuid")})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id")
    private Long id;

    @Comment("주문 UUID")
    @Column(nullable = false, length = 50)
    private String purchaseUuid;

    @Comment("회원 UUID")
    @Column(nullable = false, length = 50)
    private String memberUuid;

    @Comment("결제 ID")
    @Column(nullable = false)
    private Long paymentId;

    @Comment("주문 시간")
    @Column(nullable = false)
    private LocalDateTime purchasedAt;

    @Comment("주문 상태")
    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private PurchaseStatus status;

    @Builder
    public Purchase(String purchaseUuid, String memberUuid, Long paymentId, LocalDateTime purchasedAt, PurchaseStatus status) {
        this.purchaseUuid = purchaseUuid;
        this.memberUuid = memberUuid;
        this.paymentId = paymentId;
        this.purchasedAt = purchasedAt;
        this.status = status;
    }
}
