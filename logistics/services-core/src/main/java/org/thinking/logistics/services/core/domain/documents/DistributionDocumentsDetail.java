package org.thinking.logistics.services.core.domain.documents;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class DistributionDocumentsDetail {
    @Id
    @GeneratedValue
    private long id;
}