package org.thinking.sce.service.core.repository.parameter;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.parameter.Parameter;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface ParameterRepository extends DomainRepository<Parameter, Long> {
}