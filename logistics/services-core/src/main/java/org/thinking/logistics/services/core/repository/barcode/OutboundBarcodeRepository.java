package org.thinking.logistics.services.core.repository.barcode;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.barcode.OutboundBarcode;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface OutboundBarcodeRepository extends DomainRepository<OutboundBarcode, Long> {
}