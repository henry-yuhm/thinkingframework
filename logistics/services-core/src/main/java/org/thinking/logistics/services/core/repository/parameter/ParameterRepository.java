package org.thinking.logistics.services.core.repository.parameter;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.parameter.Parameter;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface ParameterRepository extends DomainRepository<Parameter, Long> {
}