package org.thinking.sce.service.core.repository.barcode;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.barcode.ItemBarcode;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface ItemBarcodeRepository extends DomainRepository<ItemBarcode, Long> {
}