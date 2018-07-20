package org.thinking.sce.service.core.repository.barcode;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.barcode.ShipmentBarcode;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface OutboundBarcodeRepository extends DomainRepository<ShipmentBarcode, Long> {
}