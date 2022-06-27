package com.realo.estate.domain.persistence.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.realo.estate.domain.dto.validation.ValidationMessageConst;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactInfo {

    @Column(name = "phone_number", length = 16, nullable = false)
    @Size(min = 10, max = 16, message = ValidationMessageConst.PHONE_NUMBER_BOUND)
    @NotNull(message = ValidationMessageConst.PHONE_NUMBER_NOT_NULL)
    private String phoneNumber;

    @Column(name = "preferred_messenger", length = 10)
    @Enumerated(EnumType.STRING)
    private MessengerType preferredMessenger;
}