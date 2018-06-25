package org.thinking.logistics.services.core.domain.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.thinking.logistics.services.core.domain.BaseDomainEntity;

import javax.persistence.Entity;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class DistributionDocumentHeader extends BaseDomainEntity {
}