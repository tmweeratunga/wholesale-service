package com.wholesale.api.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AccountResponse extends RepresentationModel<PlatformResponse>  {

    private String accountId;
    private String accountNumber;
    private String accountName;
    private String accountType;
    private Date balanceDate;
    private String currency;
    private BigDecimal openingBalance;
}
