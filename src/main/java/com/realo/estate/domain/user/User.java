package com.realo.estate.domain.user;

import com.realo.estate.domain.announcement.Announcement;
import com.realo.estate.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(schema = "user_storage", name = "user_account")
@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class User extends BaseEntity<Long> {

  @Column(name = "first_name", length = 64, nullable = false)
  private String firstName;

  @Column(name = "last_name", length = 64, nullable = false)
  private String lastName;

  @Column(length = 16, nullable = false)
  @Enumerated(EnumType.STRING)
  private Gender gender;

  @Column(length = 64, unique = true, nullable = false)
  private String login;

  @Column(length = 128, nullable = false, updatable = false)
  private String password;

  @Column(length = 128, unique = true, nullable = false)
  private String email;

  @Column(length = 16, nullable = false)
  @Enumerated(EnumType.STRING)
  private Role role;

  @Embedded
  private UserAddress userAddress;

  @Embedded
  private ContactInfo contactInfo;

  @NotAudited
  @OneToMany(mappedBy = "announcementAuthor", cascade = {
      CascadeType.MERGE, CascadeType.REMOVE
  })
  @Builder.Default
  @ToString.Exclude
  private List<Announcement> announcements = new ArrayList<>();

  @NotAudited
  @ManyToMany(mappedBy = "interestedUsers", cascade = {
      CascadeType.MERGE, CascadeType.REMOVE
  })
  @Builder.Default
  @ToString.Exclude
  private List<Announcement> announcementInterests = new ArrayList<>();
}