package org.thinking.sce.service.core.repository.parameter;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.parameter.ParameterRange;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface ParameterRangeRepository extends DomainRepository<ParameterRange, Long> {
}