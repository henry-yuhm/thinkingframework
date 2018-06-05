package org.thinking.logistics.services.core.domain.documents;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.BaseDomainEntity;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class DistributionDocumentsHeader extends BaseDomainEntity {
}