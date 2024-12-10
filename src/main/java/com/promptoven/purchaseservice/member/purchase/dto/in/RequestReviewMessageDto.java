package com.promptoven.purchaseservice.member.purchase.dto.in;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestReviewMessageDto {

    private String productUuid;

    private String authorUuid;
}
