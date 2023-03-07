package com.wholesale.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "ACCOUNT")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ACCOUNT_ID", nullable = false)
    private String accountId;

    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @Column(name = "ACCOUNT_NUMBER", nullable = false)
    private String accountNumber;

    @Column(name = "ACCOUNT_NAME", nullable = false)
    private String accountName;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_TYPE", nullable = false)
    private AccountType accountType;

    @Column(name = "BALANCE_DATE", nullable = false)
    private Date balanceDate;

    @ManyToOne
    @JoinColumn(name = "CURRENCY", nullable = false)
    private Currency currency;

    @Column(name = "OPENING_BALANCE", nullable = false)
    private BigDecimal openingBalance;
}
