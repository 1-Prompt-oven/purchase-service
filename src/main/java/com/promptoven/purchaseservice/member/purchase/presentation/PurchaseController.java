package com.promptoven.purchaseservice.member.purchase.presentation;

import com.promptoven.purchaseservice.global.common.CursorPage;
import com.promptoven.purchaseservice.global.common.response.BaseResponse;
import com.promptoven.purchaseservice.member.purchase.application.PurchaseService;
import com.promptoven.purchaseservice.member.purchase.dto.in.PurchaseCartRequestDto;
import com.promptoven.purchaseservice.member.purchase.dto.in.PurchaseRequestDto;
import com.promptoven.purchaseservice.member.purchase.dto.out.PurchaseProductResponseDto;
import com.promptoven.purchaseservice.member.purchase.dto.out.PurchaseResponseDto;
import com.promptoven.purchaseservice.member.purchase.vo.in.PurchaseCartRequestVo;
import com.promptoven.purchaseservice.member.purchase.vo.in.PurchaseRequestVo;
import com.promptoven.purchaseservice.member.purchase.vo.out.PurchaseProductResponseVo;
import com.promptoven.purchaseservice.member.purchase.vo.out.PurchaseResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Operation(summary = "주문 목록 조회 (페이지네이션)", description = "주문 목록 조회 (페이지네이션)")
    @GetMapping("/list")
    public BaseResponse<CursorPage<PurchaseResponseVo>> getPurchaseList(
            @RequestParam String memberUuid,
            @RequestParam(required = false) Long lastPurchaseId,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

        CursorPage<PurchaseResponseDto> purchaseResponseDtos = purchaseService.getPurchaseList(memberUuid, lastPurchaseId, pageSize);

        List<PurchaseResponseVo> purchaseResponseVos = purchaseResponseDtos.getContent().stream()
                .map(PurchaseResponseDto::toVo)
                .toList();

        return new BaseResponse<>(
                new CursorPage<>(
                        purchaseResponseVos,
                        purchaseResponseDtos.getNextCursor(),
                        purchaseResponseDtos.getHasNext(),
                        purchaseResponseDtos.getPageSize(),
                        purchaseResponseVos.size()
                )
        );
    }

    @Operation(summary = "주문 상품 목록 전체 조회 (페이지네이션)", description = "주문 상품 목록 조회 (페이지네이션)")
    @GetMapping("/products")
    public BaseResponse<CursorPage<PurchaseProductResponseVo>> getPurchaseProduct(
            @RequestParam String memberUuid,
            @RequestParam(required = false) Long lastProductId,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

        CursorPage<PurchaseProductResponseDto> purchaseProductResponseDtos = purchaseService.getPurchaseProduct(memberUuid, lastProductId, pageSize);

        List<PurchaseProductResponseVo> purchaseProductResponseVos = purchaseProductResponseDtos.getContent().stream()
                .map(PurchaseProductResponseDto::toVo)
                .toList();

        return new BaseResponse<>(
                new CursorPage<>(
                        purchaseProductResponseVos,
                        purchaseProductResponseDtos.getNextCursor(),
                        purchaseProductResponseDtos.getHasNext(),
                        purchaseProductResponseDtos.getPageSize(),
                        purchaseProductResponseVos.size()
                )
        );
    }

    // 하나의 주문에 대한 상품 목록 조회
    @Operation(summary = "주문 UUID를 통한 주문 상품 목록 조회", description = "주문 UUID를 통한 주문 상품 목록 조회")
    @GetMapping("/products/{purchaseUuid}")
    public BaseResponse<List<PurchaseProductResponseVo>> getPurchaseProductByPurchaseUuid(@PathVariable String purchaseUuid) {

        List<PurchaseProductResponseDto> purchaseProductResponseDtos = purchaseService.getPurchaseProductByPurchaseUuid(purchaseUuid);

        return new BaseResponse<>(purchaseProductResponseDtos.stream()
                .map(PurchaseProductResponseDto::toVo)
                .toList());
    }

    @Operation(summary = "주문 상세 조회", description = "주문 상세 조회")
    @GetMapping("/{purchaseUuid}")
    public BaseResponse<PurchaseResponseVo> getPurchase(@PathVariable String purchaseUuid) {

        return new BaseResponse<>(PurchaseResponseDto.toVo(purchaseService.getPurchaseByPurchaseUuid(purchaseUuid)));
    }

    @Operation(summary = "상품 구매 여부", description = "상품 구매 여부")
    @GetMapping("/check")
    public BaseResponse<Boolean> checkPurchase(@RequestParam String memberUuid, @RequestParam String productUuid) {
        return new BaseResponse<>(purchaseService.checkPurchase(memberUuid, productUuid));
    }
}
