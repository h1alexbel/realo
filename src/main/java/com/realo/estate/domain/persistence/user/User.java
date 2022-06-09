package com.realo.estate.domain.persistence.user;

import com.realo.estate.domain.persistence.BaseEntity;
import com.realo.estate.domain.persistence.announcement.Announcement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
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
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
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

    @Column(length = 128, nullable = false)
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

    public void addAnnouncementToInterests(Announcement announcement) {
        announcementInterests.add(announcement);
        announcement.getInterestedUsers().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}