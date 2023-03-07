package com.wholesale.service;

import com.wholesale.api.dto.AccountResponse;
import com.wholesale.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<AccountResponse> getAccountListByUserId(String userId){

        return accountRepository.findByUserId(userId).stream().map(
                transaction -> modelMapper.map(transaction, AccountResponse.class)
        ).collect(Collectors.toList());

    }
}
