package com.realo.estate.domain.persistence.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class UserAddress {

    @Column(length = 32, nullable = false)
    private String country;

    @Column(length = 48)
    private String city;
}