package org.thinking.logistics.services.core.repository.dictionary;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.dictionary.EmployeePost;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface EmployeePostRepository extends DomainRepository<EmployeePost, Long> {
}