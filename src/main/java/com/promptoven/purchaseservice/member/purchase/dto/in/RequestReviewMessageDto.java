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

    private Long reviewId;
    private String productUuid;
    private String sellerUuid;
    private String authorUuid;
    private String authorProfileImage;
    private String authorNickname;
    private int star;
    private String contents;
    private Boolean isDeleted;
}
