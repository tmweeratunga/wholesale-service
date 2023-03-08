package com.wholesale.unit.service;

import com.wholesale.api.dto.TransactionDetailsResponse;
import com.wholesale.repository.TransactionRepository;
import com.wholesale.repository.entity.Account;
import com.wholesale.repository.entity.Currency;
import com.wholesale.repository.entity.Transaction;
import com.wholesale.repository.entity.TransactionType;
import com.wholesale.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void getTransactionListSuccessWithData() {
        Pageable pageable = PageRequest.of(0, 1, Sort.by("valueDate"));
        Page<Transaction> transactionPage = getTransactionPage(List.of(getTransaction("TRANSACTION1")), pageable);
        when(transactionRepository.findByAccountAccountIdAndAccountUserId(any(), any(), any())).thenReturn(transactionPage);
        TransactionDetailsResponse transactionDetailsResponse = transactionService
                .getTransactionListByAccountId("ACCOUNTID", "USERID", 0, 1);
        verify(transactionRepository, times(1)).findByAccountAccountIdAndAccountUserId(any(), any(), any());
        assertNotNull(transactionDetailsResponse);
        assertFalse(transactionDetailsResponse.getTransactionDetails().isEmpty());

        assertEquals(transactionDetailsResponse.getTransactionDetails().get(0).getTransactionId(), transactionPage.getContent().get(0).getTransactionId());
        assertEquals(transactionDetailsResponse.getTransactionDetails().get(0).getAccountNumber(), transactionPage.getContent().get(0).getAccount().getAccountNumber());
        assertEquals(transactionDetailsResponse.getTransactionDetails().get(0).getAccountName(), transactionPage.getContent().get(0).getAccount().getAccountName());
        assertEquals(transactionDetailsResponse.getTransactionDetails().get(0).getValueDate(), transactionPage.getContent().get(0).getValueDate());
        assertEquals(transactionDetailsResponse.getTransactionDetails().get(0).getCurrency(), transactionPage.getContent().get(0).getCurrency().getCurrencyCode());
        assertEquals(transactionDetailsResponse.getTransactionDetails().get(0).getAmount(), transactionPage.getContent().get(0).getAmount());
        assertEquals(transactionDetailsResponse.getTransactionDetails().get(0).getTransactionType(), transactionPage.getContent().get(0).getTransactionType().getTranType());
        assertEquals(transactionDetailsResponse.getTransactionDetails().get(0).getTransactionNarrative(), transactionPage.getContent().get(0).getTransactionNarrative());
        assertEquals(transactionDetailsResponse.getPageNumber(), Integer.valueOf(pageable.getPageNumber()));
        assertEquals(transactionDetailsResponse.getPageSize(), Integer.valueOf(pageable.getPageSize()));
        assertEquals(transactionDetailsResponse.getTotalPages(), Integer.valueOf(transactionPage.getTotalPages()));
    }

    @Test
    void getTransactionListSuccessWithMultipleData() {
        Pageable pageable = PageRequest.of(0, 2, Sort.by("valueDate"));
        Page<Transaction> transactionPage = getTransactionPage(List.of(
                getTransaction("TRANSACTION_1"),
                getTransaction("TRANSACTION_2")), pageable);
        when(transactionRepository.findByAccountAccountIdAndAccountUserId(any(), any(), any())).
                thenReturn(transactionPage);
        TransactionDetailsResponse transactionDetailsResponse = transactionService
                .getTransactionListByAccountId("ACCOUNTID", "USERID", 0, 2);
        verify(transactionRepository, times(1)).findByAccountAccountIdAndAccountUserId(any(), any(), any());
        assertNotNull(transactionDetailsResponse);
        assertFalse(transactionDetailsResponse.getTransactionDetails().isEmpty());
        assertEquals(2, transactionDetailsResponse.getTransactionDetails().size());

    }

    @Test
    void getTransactionListSuccessWithEmptyData() {
        Pageable pageable = PageRequest.of(0, 1, Sort.by("valueDate"));
        when(transactionRepository.findByAccountAccountIdAndAccountUserId(any(), any(), any())).
                thenReturn(new PageImpl<>(new ArrayList<>(), pageable, 0));
        TransactionDetailsResponse transactionDetailsResponse = transactionService
                .getTransactionListByAccountId("ACCOUNTID", "USERID", 0, 1);
        verify(transactionRepository, times(1)).findByAccountAccountIdAndAccountUserId(any(), any(), any());
        assertTrue(transactionDetailsResponse.getTransactionDetails().isEmpty());
    }


    private Page<Transaction> getTransactionPage(List<Transaction> transaction, Pageable pageable) {
        return new PageImpl<>(transaction, pageable, 5);

    }

    private Transaction getTransaction(String transactionId) {
        Transaction transaction = Transaction.builder()
                .transactionId(transactionId)
                .transactionType(TransactionType.Credit)
                .amount(BigDecimal.valueOf(12.78))
                .currency(Currency.builder().currencyCode("AUD").build())
                .valueDate(new Date(1678169817163L))
                .account(Account.builder().accountNumber("123456").accountName("NAME").build())
                .transactionNarrative("TRANSACTIONNARRATIVE")
                .build();
        return transaction;
    }
}
