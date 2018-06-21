package org.thinking.logistics.services.core.repository.dictionary;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.dictionary.ItemCategory;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface ItemCategoryRepository extends DomainRepository<ItemCategory, Long> {
}