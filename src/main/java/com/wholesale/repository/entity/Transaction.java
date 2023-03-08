package com.wholesale.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "TRANSACTION")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Transaction {

    @Id
    @GeneratedValue
    @Column(name = "TRANSACTION_ID", unique = true)
    private String transactionId;

    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    @ManyToOne
    private Account account;

    @Column(name = "VALUE_DATE", nullable = false)
    private Date valueDate;

    @JoinColumn(name = "CURRENCY", nullable = false)
    @ManyToOne
    private Currency currency;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    @Column(name = "TRANSACTION_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "TRANSACTION_NARRATIVE")
    private String transactionNarrative;
}
