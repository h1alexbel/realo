package com.realo.estate.domain.dto;

import com.realo.estate.domain.persistence.estate.EstateAddress;
import com.realo.estate.domain.persistence.estate.EstateType;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Value
@Builder
public class EstateDto implements Serializable {

    Long id;
    EstateType estateType;
    EstateAddress address;
    Double square;
    String description;
    Integer yearOfConstruction;
    ProviderDto provider;
}