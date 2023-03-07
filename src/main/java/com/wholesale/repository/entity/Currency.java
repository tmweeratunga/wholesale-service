package com.wholesale.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CURRENCY")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Currency {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue
    private Long id;

    @Column(name = "CURRENCY_CODE", nullable = false)
    private String currencyCode;

    @Column(name = "NAME", nullable = false)
    private String name;
}
