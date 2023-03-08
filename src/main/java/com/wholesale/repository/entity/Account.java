package com.wholesale.repository.entity;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
    @GeneratedValue
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
