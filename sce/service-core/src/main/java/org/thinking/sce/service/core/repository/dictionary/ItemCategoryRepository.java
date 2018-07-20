package org.thinking.sce.service.core.repository.dictionary;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.dictionary.ItemCategory;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface ItemCategoryRepository extends DomainRepository<ItemCategory, Long> {
}