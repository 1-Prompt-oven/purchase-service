package com.promptoven.purchaseservice.common.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PurchaseTemp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_temp_id")
    private Long id;

    @Comment("회원 UUID")
    @Column(nullable = false, length = 50)
    private String memberUuid;

    @Comment("상품 UUID")
    @Column(nullable = false, length = 50)
    private String productUuid;

    @Comment("상품명")
    @Column(nullable = false, length = 100)
    private String productName;

    @Comment("가격")
    @Column(nullable = false)
    private Double price;

    @Builder
    public PurchaseTemp(String memberUuid, String productUuid, String productName, Double price) {
        this.memberUuid = memberUuid;
        this.productUuid = productUuid;
        this.productName = productName;
        this.price = price;
    }
}
