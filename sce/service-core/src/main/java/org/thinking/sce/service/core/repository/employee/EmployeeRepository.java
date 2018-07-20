package org.thinking.sce.service.core.repository.employee;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.employee.Employee;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface EmployeeRepository extends DomainRepository<Employee, Long> {
}