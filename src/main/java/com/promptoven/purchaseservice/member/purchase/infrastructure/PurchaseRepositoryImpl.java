package com.promptoven.purchaseservice.member.purchase.infrastructure;

import com.promptoven.purchaseservice.common.domain.QPurchase;
import com.promptoven.purchaseservice.common.domain.QPurchaseProduct;
import com.promptoven.purchaseservice.global.common.CursorPage;
import com.promptoven.purchaseservice.member.purchase.dto.out.PurchaseProductResponseDto;
import com.promptoven.purchaseservice.member.purchase.dto.out.PurchaseResponseDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
@Repository
public class PurchaseRepositoryImpl implements PurchaseRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public CursorPage<PurchaseResponseDto> getPurchaseListWithPagination(String memberUuid, Long lastPurchaseId, Integer pageSize) {
        QPurchase purchase = QPurchase.purchase;
        BooleanBuilder whereClause = new BooleanBuilder();

        whereClause.and(purchase.memberUuid.eq(memberUuid));
        if (lastPurchaseId != null) {
            whereClause.and(purchase.id.lt(lastPurchaseId)); // Cursor 조건
        }

        List<PurchaseResponseDto> purchases = jpaQueryFactory
                .select(purchase)
                .from(purchase)
                .where(whereClause)
                .orderBy(purchase.id.desc())
                .limit(pageSize + 1) // pageSize + 1로 가져와서 다음 페이지 유무 확인
                .fetch()
                .stream()
                .map(PurchaseResponseDto::fromEntity)
                .toList();

        return buildCursorPage(purchases, pageSize, PurchaseResponseDto::getId);
    }

    @Override
    public CursorPage<PurchaseProductResponseDto> getPurchaseProductWithPagination(String memberUuid, Long lastProductId, Integer pageSize) {
        QPurchaseProduct purchaseProduct = QPurchaseProduct.purchaseProduct;
        BooleanBuilder whereClause = new BooleanBuilder();

        whereClause.and(purchaseProduct.memberUuid.eq(memberUuid));
        if (lastProductId != null) {
            whereClause.and(purchaseProduct.id.lt(lastProductId)); // Cursor 조건
        }

        List<PurchaseProductResponseDto> purchaseProducts = jpaQueryFactory
                .select(purchaseProduct)
                .from(purchaseProduct)
                .where(whereClause)
                .orderBy(purchaseProduct.id.desc())
                .limit(pageSize + 1)
                .fetch()
                .stream()
                .map(PurchaseProductResponseDto::fromEntity)
                .toList();

        return buildCursorPage(purchaseProducts, pageSize, PurchaseProductResponseDto::getId);
    }

    private <T> CursorPage<T> buildCursorPage(List<T> content, int pageSize, Function<T, Long> idExtractor) {
        boolean hasNext = content.size() > pageSize;
        Long nextCursor = hasNext ? idExtractor.apply(content.get(pageSize - 1)) : null;
        if (hasNext) {
            content = content.subList(0, pageSize);
        }

        return new CursorPage<>(content, nextCursor, hasNext, pageSize, content.size());
    }

}
