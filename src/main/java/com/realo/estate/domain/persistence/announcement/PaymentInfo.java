package com.realo.estate.domain.persistence.announcement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class PaymentInfo {

    private BigDecimal price;

    @Column(length = 3)
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;

    @Column(name = "is_loan_possible", nullable = false)
    private boolean isLoanPossible;
}