package org.thinking.logistics.services.core.repository;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.core.Item;

@Repository
public interface ItemRepository extends DomainRepository<Item, Long> {
}