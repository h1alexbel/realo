package com.realo.estate.domain.persistence.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ContactInfo {

    @Column(name = "phone_number", length = 16, nullable = false)
    private String phoneNumber;

    @Column(name = "preferred_messenger", length = 10)
    @Enumerated(EnumType.STRING)
    private MessengerType preferredMessenger;
}