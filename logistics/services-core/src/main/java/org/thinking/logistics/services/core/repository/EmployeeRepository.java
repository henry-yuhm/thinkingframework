package org.thinking.logistics.services.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.entity.Owner;
import org.thinking.logistics.services.core.entity.employee.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByOwnerAndNo(Owner owner, String no);
}