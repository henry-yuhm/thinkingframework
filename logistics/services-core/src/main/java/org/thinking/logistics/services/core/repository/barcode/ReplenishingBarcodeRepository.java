package org.thinking.logistics.services.core.repository.barcode;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.barcode.ReplenishingBarcode;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface ReplenishingBarcodeRepository extends DomainRepository<ReplenishingBarcode, Long> {
}