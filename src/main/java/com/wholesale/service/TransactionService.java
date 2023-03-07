package com.wholesale.service;

import com.wholesale.api.dto.TransactionDetails;
import com.wholesale.api.dto.TransactionDetailsResponse;
import com.wholesale.repository.TransactionRepository;
import com.wholesale.repository.entity.Transaction;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ModelMapper modelMapper;

    public TransactionDetailsResponse getTransactionListByAccountId(String accountId, String userId, int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("valueDate"));
        Page<Transaction> pageData = transactionRepository
                .findByAccountAccountIdAndAccountUserId(accountId, userId, pageable);

        List<TransactionDetails> transactionDetails = pageData.stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDetails.class))
                .collect(Collectors.toList());

        return TransactionDetailsResponse.builder()
                .transactionDetails(transactionDetails)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .totalCount(pageData.getTotalElements())
                .totalPages(pageData.getTotalPages())
                .build();
    }
}
