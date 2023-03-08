package com.wholesale.api.hateoas;

import com.wholesale.api.controller.AccountController;
import com.wholesale.api.dto.AccountResponse;
import com.wholesale.api.dto.PlatformResponse;
import com.wholesale.api.dto.TransactionDetailsResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.nio.file.AccessDeniedException;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AccountControllerSupport {

    public void addGetUserTransactionsHateoasSupport(String accountId, int pageNumber, int pageSize, TransactionDetailsResponse data, PlatformResponse platformResponse) throws AccessDeniedException {
        HttpHeaders headers = new HttpHeaders();
        platformResponse.add(linkTo(methodOn(AccountController.class).getUserTransactionList(headers, accountId, pageNumber, pageSize)).withSelfRel());
        platformResponse.add(linkTo(methodOn(AccountController.class).getUserAccountList(headers)).withRel("getUserAccountList"));
        if(data != null && !data.getTransactionDetails().isEmpty()) {
            if (data.getTotalPages() - 1 != pageNumber)
                platformResponse.add(linkTo(methodOn(AccountController.class).getUserTransactionList(headers, accountId, pageNumber + 1, pageSize)).withRel("nextPage"));
            if (pageNumber > 0)
                platformResponse.add(linkTo(methodOn(AccountController.class).getUserTransactionList(headers, accountId, pageNumber - 1, pageSize)).withRel("backPage"));
        }
    }

    public void addAccountResponseHateoasSupport(AccountResponse accountResponse) throws AccessDeniedException {
        HttpHeaders headers = new HttpHeaders();
        accountResponse.add(linkTo(methodOn(AccountController.class).getUserTransactionList(headers, accountResponse.getAccountId(), 0, 5)).withRel("getUserTransactionList"));
    }

    public void addAccountResponseHateoasSupport(List<AccountResponse> accountResponseList) throws AccessDeniedException {
        for (AccountResponse accountResponse : accountResponseList) {
            addAccountResponseHateoasSupport(accountResponse);
        }
    }

    public void addGetUserAccountsHateoasSupport(PlatformResponse platformResponse) throws AccessDeniedException {
        platformResponse.add(linkTo(methodOn(AccountController.class).getUserAccountList(new HttpHeaders())).withSelfRel());
    }
}
