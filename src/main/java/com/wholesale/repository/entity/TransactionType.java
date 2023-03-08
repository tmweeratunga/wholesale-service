package com.wholesale.repository.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TransactionType {
    Debit("Debit"),
    Credit("Credit");

    private final String tranType;
}
