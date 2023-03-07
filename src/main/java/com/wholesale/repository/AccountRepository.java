package com.wholesale.repository;


import com.wholesale.repository.entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface AccountRepository extends CrudRepository<Account, Integer> {

    List<Account> findByUserId(String userId);
}
