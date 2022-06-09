package com.realo.estate.domain.persistence.announcement;

import com.realo.estate.domain.persistence.BaseEntity;
import com.realo.estate.domain.persistence.estate.Estate;
import com.realo.estate.domain.persistence.user.User;
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
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(schema = "announcement_storage", name = "announcement")
@Entity
@EntityListeners(AuditingEntityListener.class)
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class Announcement extends BaseEntity<Long> {

    @Column(name = "announcement_type", length = 64, nullable = false)
    @Enumerated(EnumType.STRING)
    private AnnouncementType announcementType;

    @Column(length = 64, unique = true, nullable = false)
    private String title;

    @Column(length = 512)
    private String details;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estate_id")
    @ToString.Exclude
    private Estate estate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User announcementAuthor;

    @NotAudited
    @ManyToMany(cascade = {
            CascadeType.MERGE, CascadeType.REMOVE
    })
    @JoinTable(
            name = "user_interests",
            schema = "user_storage",
            joinColumns = @JoinColumn(name = "announcement_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @Builder.Default
    @ToString.Exclude
    private List<User> interestedUsers = new ArrayList<>();

    @Embedded
    private PaymentInfo paymentInfo;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDate createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Announcement that = (Announcement) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}