package org.thinking.sce.service.core.repository;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.common.Item;

@Repository
public interface ItemRepository extends DomainRepository<Item, Long> {
}