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
import org.springframework.data.domain.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void getTransactionListSuccessWithData(){
        Pageable pageable = PageRequest.of(0, 1, Sort.by("valueDate"));
        when(transactionRepository.findByAccountAccountIdAndAccountUserId(any(),any(),any())).
                thenReturn(getTransactionPage("TRANSACTION1",pageable));
        TransactionDetailsResponse transactionDetailsResponse = transactionService
                .getTransactionListByAccountId("ACCOUNTID","USERID",0,1);
        verify(transactionRepository,times(1)).findByAccountAccountIdAndAccountUserId(any(),any(),any());
        assertNotNull(transactionDetailsResponse);
        assertFalse(transactionDetailsResponse.getTransactionDetails().isEmpty());

        assertEquals(transactionDetailsResponse.getTransactionDetails().get(0).getTransactionId(),"TRANSACTION1");
        assertEquals(transactionDetailsResponse.getTransactionDetails().get(0).getAccountNumber(),"123456");
        assertEquals(transactionDetailsResponse.getTransactionDetails().get(0).getAccountName(),"NAME");
        assertEquals(transactionDetailsResponse.getTransactionDetails().get(0).getValueDate(),new Date(1678169817163L));
        assertEquals(transactionDetailsResponse.getTransactionDetails().get(0).getCurrency(),"AUD");
        assertEquals(transactionDetailsResponse.getTransactionDetails().get(0).getAmount(),BigDecimal.valueOf(12.78));
        assertEquals(transactionDetailsResponse.getTransactionDetails().get(0).getTransactionType(),"Credit");
        assertEquals(transactionDetailsResponse.getTransactionDetails().get(0).getTransactionNarrative(),"TRANSACTIONNARRATIVE");
    }

    @Test
    void getTransactionListSuccessWithEmptyData(){
        Pageable pageable = PageRequest.of(0, 1, Sort.by("valueDate"));
        when(transactionRepository.findByAccountAccountIdAndAccountUserId(any(),any(),any())).
                thenReturn(new PageImpl<>(new ArrayList<>(), pageable, 0));
        TransactionDetailsResponse transactionDetailsResponse = transactionService
                .getTransactionListByAccountId("ACCOUNTID","USERID",0,1);
        verify(transactionRepository,times(1)).findByAccountAccountIdAndAccountUserId(any(),any(),any());
        assertTrue(transactionDetailsResponse.getTransactionDetails().isEmpty());
    }


    private Page<Transaction> getTransactionPage(String transactionId, Pageable pageable){
        Transaction transaction = Transaction.builder()
                .transactionId(transactionId)
                .transactionType(TransactionType.Credit)
                .amount(BigDecimal.valueOf(12.78))
                .currency(Currency.builder().currencyCode("AUD").build())
                .valueDate(new Date(1678169817163L))
                .account(Account.builder().accountNumber("123456").accountName("NAME").build())
                .transactionNarrative("TRANSACTIONNARRATIVE")
                .build();
        //Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("valueDate"));
        return new PageImpl<>(List.of(transaction), pageable, 0);

    }
}
