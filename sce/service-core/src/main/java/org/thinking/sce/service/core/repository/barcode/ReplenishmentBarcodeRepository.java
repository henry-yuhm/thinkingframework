package org.thinking.sce.service.core.repository.barcode;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.barcode.ReplenishmentBarcode;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface ReplenishmentBarcodeRepository extends DomainRepository<ReplenishmentBarcode, Long> {
}