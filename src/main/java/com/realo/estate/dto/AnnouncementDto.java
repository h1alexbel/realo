package com.realo.estate.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.realo.estate.domain.announcement.AnnouncementType;
import com.realo.estate.domain.announcement.PaymentInfo;
import com.realo.estate.dto.validation.ValidationMessageConst;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnnouncementDto implements Serializable {

  Long id;

  @NotNull(message = ValidationMessageConst.ANNOUNCEMENT_TYPE_NOT_NULL)
  AnnouncementType announcementType;

  @Size(max = 64, message = ValidationMessageConst.TITLE_BOUND)
  @NotNull(message = ValidationMessageConst.TITLE_NOT_NULL)
  String title;

  @Size(max = 512, message = ValidationMessageConst.DETAILS_BOUND)
  String details;

  EstateDto estate;

  UserDto announcementAuthor;

  List<UserDto> interestedUsers;

  PaymentInfo paymentInfo;

  LocalDate createdAt;
}