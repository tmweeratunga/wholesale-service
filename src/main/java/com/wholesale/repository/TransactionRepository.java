package com.wholesale.repository;


import com.wholesale.repository.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    Page<Transaction> findByAccountAccountIdAndAccountUserId(String accountId, String userId, Pageable pageable);
}
