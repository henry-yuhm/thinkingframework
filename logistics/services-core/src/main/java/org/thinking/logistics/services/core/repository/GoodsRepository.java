package org.thinking.logistics.services.core.repository;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.Goods;

@Repository
public interface GoodsRepository extends DomainRepository<Goods, Long> {
}