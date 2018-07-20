package org.thinking.sce.service.core.repository.table;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.table.ReviewSlide;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface ReviewSlideRepository extends DomainRepository<ReviewSlide, Long> {
}