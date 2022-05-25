package com.realo.estate.repository.filter;

import com.realo.estate.model.announcement.AnnouncementType;
import com.realo.estate.model.announcement.CurrencyType;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class AnnouncementFilter {

    AnnouncementType announcementType;
    Integer yearOfConstruction;
    Double minSquare;
    Double maxSquare;
    CurrencyType currencyType;
    BigDecimal minPrice;
    BigDecimal maxPrice;
}