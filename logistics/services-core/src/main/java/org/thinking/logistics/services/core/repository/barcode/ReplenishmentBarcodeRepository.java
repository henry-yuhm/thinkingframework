package org.thinking.logistics.services.core.repository.barcode;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.barcode.ReplenishmentBarcode;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface ReplenishmentBarcodeRepository extends DomainRepository<ReplenishmentBarcode, Long> {
}