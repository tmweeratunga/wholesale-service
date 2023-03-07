package com.wholesale.api.controller;

import com.wholesale.api.dto.AccountResponse;
import com.wholesale.api.dto.PlatformResponse;
import com.wholesale.api.dto.TransactionDetailsResponse;
import com.wholesale.api.hateoas.AccountControllerSupport;
import com.wholesale.config.security.TokenExtractor;
import com.wholesale.service.AccountService;
import com.wholesale.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Validated
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountControllerSupport accountControllerSupport;

    @GetMapping("user/accounts")
    public PlatformResponse getUserAccountList(@RequestHeader final HttpHeaders httpHeaders) throws AccessDeniedException {
        List<AccountResponse> data = accountService.getAccountListByUserId(TokenExtractor.getUserIdFromHeader(httpHeaders));
        accountControllerSupport.addAccountResponseHateoasSupport(data);
        PlatformResponse response =  PlatformResponse.builder()
                .data(data)
                .success(true)
                .build();
        accountControllerSupport.addGetUserAccountsHateoasSupport(response);
        return response;
    }

    @GetMapping("/user/accounts/{account-id}/transactions")
    public PlatformResponse getUserTransactionList(@RequestHeader final HttpHeaders httpHeaders,
                                               @PathVariable(name = "account-id") final String accountId,
                                               @RequestParam @Min(value = 0, message = "Page number should be zero or positive integer") final int pageNumber,
                                               @RequestParam @Min(value = 1, message = "Page size should be positive integer") final int pageSize) throws AccessDeniedException {
        TransactionDetailsResponse data = transactionService.getTransactionListByAccountId(accountId,
                TokenExtractor.getUserIdFromHeader(httpHeaders),
                pageNumber,
                pageSize);
        PlatformResponse platformResponse =  PlatformResponse.builder()
                .data(data)
                .success(true)
                .build();
        accountControllerSupport.addGetUserTransactionsHateoasSupport(accountId, pageNumber, pageSize, data, platformResponse);

        return platformResponse;
    }
}
