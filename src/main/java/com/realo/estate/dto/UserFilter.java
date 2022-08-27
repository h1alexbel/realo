package com.realo.estate.dto;

import com.realo.estate.domain.user.MessengerType;
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