package org.thinking.sce.service.core.repository.dictionary;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.dictionary.EmployeeRole;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface EmployeeRoleRepository extends DomainRepository<EmployeeRole, Long> {
}