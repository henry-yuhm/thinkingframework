package org.thinking.logistics.services.core.repository.table;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.table.ReviewSlide;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface ReviewSlideRepository extends DomainRepository<ReviewSlide, Long> {
}