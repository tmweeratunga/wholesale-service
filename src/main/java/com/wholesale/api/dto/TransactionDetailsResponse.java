package com.wholesale.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TransactionDetailsResponse {

    private List<TransactionDetails> transactionDetails;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalCount;
    private Integer totalPages;
}
