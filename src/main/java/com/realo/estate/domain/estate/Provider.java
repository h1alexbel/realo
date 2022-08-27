package com.realo.estate.domain.estate;

import com.realo.estate.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "name")
@Builder
@Table(schema = "estate_storage", name = "provider")
@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class Provider extends BaseEntity<Long> {

  @Column(length = 64, unique = true, nullable = false)
  private String name;

  @Column(name = "web_site_link", unique = true, length = 256)
  private String webSiteLink;

  @NotAudited
  @OneToMany(mappedBy = "provider", cascade = {
      CascadeType.MERGE, CascadeType.REMOVE
  })
  @Builder.Default
  @ToString.Exclude
  private List<Estate> projects = new ArrayList<>();
}