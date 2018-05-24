package org.thinking.logistics.services.core.repository.employee;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.employee.Employee;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface EmployeeRepository extends DomainRepository<Employee, Long> {
}