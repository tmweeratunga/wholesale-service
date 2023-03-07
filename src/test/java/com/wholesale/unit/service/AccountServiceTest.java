package com.wholesale.unit.service;

import com.wholesale.api.dto.AccountResponse;
import com.wholesale.repository.AccountRepository;
import com.wholesale.repository.entity.Account;
import com.wholesale.repository.entity.AccountType;
import com.wholesale.repository.entity.Currency;
import com.wholesale.service.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void getAccountListByUserIdSuccessWithData(){
        when(accountRepository.findByUserId(anyString())).thenReturn(getAccountList());
        List<AccountResponse> accountResponseList = accountService.getAccountListByUserId(anyString());
        verify(accountRepository,times(1)).findByUserId(any());
        assertFalse(accountResponseList.isEmpty());
        assertTrue(accountResponseList.size() == 1);
        assertEquals(accountResponseList.get(0).getAccountId(),"ACCOUNTID");
        assertEquals(accountResponseList.get(0).getAccountName(),"NAME");
        assertEquals(accountResponseList.get(0).getAccountType(),"Savings");
        assertEquals(accountResponseList.get(0).getAccountNumber(),"123456");
        assertEquals(accountResponseList.get(0).getBalanceDate(),new Date(1678169817163L));
        assertEquals(accountResponseList.get(0).getOpeningBalance(),BigDecimal.valueOf(123.50));
        assertEquals(accountResponseList.get(0).getCurrency(),"AUD");

    }

    @Test
    void getAccountListByUserIdSuccessWithEmptyData(){
        when(accountRepository.findByUserId(anyString())).thenReturn(new ArrayList<>());
        List<AccountResponse> accountResponseList = accountService.getAccountListByUserId(anyString());
        verify(accountRepository,times(1)).findByUserId(any());
        assertTrue(accountResponseList.isEmpty());
    }

    private List<Account> getAccountList(){
        List<Account> accountList = new ArrayList<>();
        accountList.add(Account.builder()
                .accountId("ACCOUNTID")
                .accountName("NAME")
                .accountType(AccountType.builder().id(1L).type("Savings").description("description").build())
                .accountNumber("123456")
                .balanceDate(new Date(1678169817163L))
                .openingBalance(BigDecimal.valueOf(123.50))
                .currency(Currency.builder().id(1L).currencyCode("AUD").name("Australian Dollar").build())
                .userId("USERID")
                .build());
        return accountList;
    }
}
