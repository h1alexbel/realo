package com.realo.estate.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.realo.estate.domain.dto.validation.ValidationMessageConst;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProviderDto implements Serializable {

    Long id;

    @Size(min = 3, max = 64, message = ValidationMessageConst.PROVIDER_NAME_BOUND)
    @NotNull(message = ValidationMessageConst.PROVIDER_NAME_NOT_NULL)
    String name;

    @Size(max = 256, message = ValidationMessageConst.PROVIDER_LINK_BOUND)
    @NotNull(message = ValidationMessageConst.PROVIDER_LINK_NOT_NULL)
    String webSiteLink;
}