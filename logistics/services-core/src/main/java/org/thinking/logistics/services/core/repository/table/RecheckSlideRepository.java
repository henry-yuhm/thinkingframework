package org.thinking.logistics.services.core.repository.table;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.table.RecheckSlide;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface RecheckSlideRepository extends DomainRepository<RecheckSlide, Long> {
}