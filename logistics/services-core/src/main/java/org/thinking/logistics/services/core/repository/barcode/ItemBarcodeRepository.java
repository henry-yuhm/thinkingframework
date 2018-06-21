package org.thinking.logistics.services.core.repository.barcode;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.barcode.ItemBarcode;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface ItemBarcodeRepository extends DomainRepository<ItemBarcode, Long> {
}