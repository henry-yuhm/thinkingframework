package org.thinking.logistics.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.core.entity.Employee;
import org.thinking.logistics.core.entity.Owner;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByOwnerAndNumber(Owner owner, String number);
}