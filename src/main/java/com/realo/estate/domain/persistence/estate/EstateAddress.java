package com.realo.estate.domain.persistence.estate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.realo.estate.domain.dto.validation.ValidationMessageConst;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EstateAddress {

    @Column(length = 32, nullable = false)
    @Size(max = 32, message = ValidationMessageConst.ESTATE_COUNTRY_BOUND)
    @NotNull(message = ValidationMessageConst.ESTATE_COUNTRY_NOT_NULL)
    private String country;

    @Column(length = 32, nullable = false)
    @Size(max = 32, message = ValidationMessageConst.ESTATE_CITY_BOUND)
    @NotNull(message = ValidationMessageConst.ESTATE_CITY_NOT_NULL)
    private String city;

    @Column(length = 64)
    @Size(max = 64, message = ValidationMessageConst.ESTATE_DISTRICT_BOUND)
    private String district;

    @Column(length = 64, nullable = false)
    @Size(max = 64, message = ValidationMessageConst.ESTATE_STREET_BOUND)
    @NotNull(message = ValidationMessageConst.ESTATE_STREET_NOT_NULL)
    private String street;

    @Column(length = 128, nullable = false)
    @Size(max = 128, message = ValidationMessageConst.ESTATE_STATION_BOUND)
    @NotNull(message = ValidationMessageConst.ESTATE_STATION_NOT_NULL)
    private String closestMetroStation;
}