package com.promptoven.purchaseservice.member.purchase.presentation;

import com.promptoven.purchaseservice.global.common.response.BaseResponse;
import com.promptoven.purchaseservice.member.purchase.application.PurchaseService;
import com.promptoven.purchaseservice.member.purchase.dto.in.PurchaseCartRequestDto;
import com.promptoven.purchaseservice.member.purchase.dto.in.PurchaseRequestDto;
import com.promptoven.purchaseservice.member.purchase.vo.in.PurchaseCartRequestVo;
import com.promptoven.purchaseservice.member.purchase.vo.in.PurchaseRequestVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tag(name = "구매 Member API", description = "구매 관련 API endpoints")
@RequestMapping("/v1/member/purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;

    // 상품 주문
    @Operation(summary = "상품 주문", description = "상품 주문")
    @PostMapping
    public BaseResponse<Void> purchaseProduct(@RequestBody PurchaseRequestVo requestVo) {
        purchaseService.createPurchase(PurchaseRequestDto.toDto(requestVo));
        return new BaseResponse<>();
    }

    // 장바구니 상품 주문
    @Operation(summary = "장바구니 상품 주문", description = "장바구니 상품 주문")
    @PostMapping("/cart")
    public BaseResponse<Void> purchaseCartProduct(@RequestBody PurchaseCartRequestVo requestVo) {
        purchaseService.createCartPurchase(PurchaseCartRequestDto.toDto(requestVo));
        return new BaseResponse<>();
    }
}
