package com.realo.estate.model.estate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class EstateAddress {

    @Column(length = 32, nullable = false)
    private String country;

    @Column(length = 32, nullable = false)
    private String city;

    @Column(length = 64)
    private String district;

    @Column(length = 64, nullable = false)
    private String street;

    @Column(length = 128, nullable = false)
    private String closestMetroStation;
}