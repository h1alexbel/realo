package com.realo.estate.domain.dto;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Value
@Builder
public class ProviderDto implements Serializable {

    Long id;
    String name;
    String webSiteLink;
}