package com.realo.estate.repository.filter;

import com.realo.estate.domain.persistence.announcement.AnnouncementType;
import com.realo.estate.domain.persistence.announcement.CurrencyType;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class AnnouncementFilter {

    AnnouncementType announcementType;
    String country;
    String city;
    String district;
    String metroStation;
    Integer yearOfConstruction;
    Double fromSquare;
    Double toSquare;
    CurrencyType currencyType;
    BigDecimal fromPrice;
    BigDecimal toPrice;
    String authorLogin;
    Boolean isLoanable;
}