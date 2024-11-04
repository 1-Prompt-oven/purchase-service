package com.promptoven.purchaseservice.common.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor
@Entity
public class PurchaseProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("주문 UUID")
    @Column(nullable = false, length = 50)
    private String purchaseUuid;

    @Comment("상품 UUID")
    @Column(nullable = false, length = 50)
    private String productUuid;

    @Comment("상품 이름")
    @Column(nullable = false, length = 50)
    private String productName;

    @Comment("상품 가격")
    @Column(nullable = false)
    private int price;

    @Comment("프롬프트")
    @Column(nullable = false)
    private String prompt;

    @Comment("리뷰 작성 여부")
    @Column(nullable = false)
    private boolean writtenReview;
}
