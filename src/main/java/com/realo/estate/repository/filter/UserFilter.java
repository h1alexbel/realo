package com.realo.estate.repository.filter;

import com.realo.estate.domain.persistence.user.MessengerType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserFilter {

    String firstName;
    String lastName;
    String country;
    String city;
    String phoneNumber;
    MessengerType messengerType;
}