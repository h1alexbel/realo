package com.realo.estate.domain.dto;

import com.realo.estate.domain.persistence.announcement.AnnouncementType;
import com.realo.estate.domain.persistence.announcement.PaymentInfo;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Value
@Builder
public class AnnouncementDto implements Serializable {

    Long id;
    AnnouncementType announcementType;
    String title;
    String details;
    EstateDto estate;
    UserDto announcementAuthor;
    List<UserDto> interestedUsers;
    PaymentInfo paymentInfo;
    LocalDate createdAt;
}