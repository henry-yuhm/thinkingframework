package org.thinking.logistics.services.core.repository.barcode;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.barcode.GoodsBarcode;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface GoodsBarcodeRepository extends DomainRepository<GoodsBarcode, Long> {
}