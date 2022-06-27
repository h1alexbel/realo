package com.realo.estate.domain.persistence.user;

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
public class UserAddress {

    @Column(length = 32, nullable = false)
    @Size(max = 32, message = ValidationMessageConst.COUNTRY_BOUND)
    @NotNull(message = ValidationMessageConst.COUNTRY_NOT_NULL)
    private String country;

    @Column(length = 48)
    @Size(max = 48, message = ValidationMessageConst.CITY_BOUND)
    private String city;
}