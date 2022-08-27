package com.realo.estate.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.realo.estate.domain.estate.EstateAddress;
import com.realo.estate.domain.estate.EstateType;
import com.realo.estate.dto.validation.ValidationMessageConst;
import lombok.Builder;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EstateDto implements Serializable {

  Long id;

  @NotNull(message = ValidationMessageConst.ESTATE_TYPE_NOT_NULL)
  EstateType estateType;

  @Valid
  EstateAddress address;

  @NotNull(message = ValidationMessageConst.ESTATE_SQUARE_NOT_NULL)
  Double square;

  @Size(max = 256, message = ValidationMessageConst.DESCRIPTION_BOUND)
  @NotNull(message = ValidationMessageConst.DESCRIPTION_NOT_NULL)
  String description;

  @NotNull(message = ValidationMessageConst.ESTATE_YEAR_NOT_NULL)
  Integer yearOfConstruction;

  ProviderDto provider;
}