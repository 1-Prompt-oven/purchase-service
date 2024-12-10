package com.promptoven.purchaseservice.member.purchase.dto.in;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestReviewMessageDto {

    private String authorUuid;

    private String productUuid;
}
