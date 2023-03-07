package com.wholesale.api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransactionDetails {

    private String transactionId;
    private String accountNumber;
    private String accountName;
    private Date valueDate;
    private String currency;
    private BigDecimal amount;
    private String transactionType;
    private String transactionNarrative;
}
