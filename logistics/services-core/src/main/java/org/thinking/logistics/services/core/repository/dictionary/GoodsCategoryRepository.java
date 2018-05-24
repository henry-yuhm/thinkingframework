package org.thinking.logistics.services.core.repository.dictionary;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.dictionary.GoodsCategory;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface GoodsCategoryRepository extends DomainRepository<GoodsCategory, Long> {
}