package com.wholesale.repository.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TransactionType {
    Debit("Debit"),
    Credit("Credit");

    private final String tranType;
}
