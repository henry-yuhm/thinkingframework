package org.thinking.logistics.services.core.repository.dictionary;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.dictionary.EmployeeRole;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface EmployeeRoleRepository extends DomainRepository<EmployeeRole, Long> {
}