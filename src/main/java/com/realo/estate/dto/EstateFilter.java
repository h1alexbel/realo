package com.realo.estate.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class EstateFilter {

  String providerName;
  String country;
  String city;
  String district;
  String street;
  String metroStation;
  Integer yearFrom;
  Integer yearTo;
  Double squareFrom;
  Double squareTo;
}