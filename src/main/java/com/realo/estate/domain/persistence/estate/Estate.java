package com.realo.estate.domain.persistence.estate;

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
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(schema = "estate_storage", name = "estate")
@Entity
@ToString
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class Estate extends BaseEntity<Long> {

    @Column(name = "estate_type", length = 48, nullable = false)
    @Enumerated(EnumType.STRING)
    private EstateType estateType;

    @Embedded
    private EstateAddress address;

    @Column(nullable = false)
    private Double square;

    @Column(length = 512, nullable = false)
    private String description;

    @Column(name = "year_of_construction", nullable = false)
    private Integer yearOfConstruction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    @ToString.Exclude
    private Provider provider;

    @NotAudited
    @OneToMany(mappedBy = "estate", cascade = {
            CascadeType.MERGE, CascadeType.REMOVE
    })
    @Builder.Default
    @ToString.Exclude
    private List<Announcement> announcements = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Estate estate = (Estate) o;
        return id != null && Objects.equals(id, estate.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}