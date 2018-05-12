package org.thinking.logistics.services.core.repository;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.parameter.Parameter;

@Repository
public interface ParameterRepository extends DomainRepository<Parameter, Long> {
    Parameter findByNo(String no);
}