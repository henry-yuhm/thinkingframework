package org.thinking.logistics.services.core.repository.parameter;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.parameter.ParameterRange;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface ParameterRangeRepository extends DomainRepository<ParameterRange, Long> {
}