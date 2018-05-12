package org.thinking.logistics.services.core.repository;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.Owner;
import org.thinking.logistics.services.core.domain.employee.Employee;

@Repository
public interface EmployeeRepository extends DomainRepository<Employee, Long> {
    Employee findByOwnerAndNo(Owner owner, String no);
}