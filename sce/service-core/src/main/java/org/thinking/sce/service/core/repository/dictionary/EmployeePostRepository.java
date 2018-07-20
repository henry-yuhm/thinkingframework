package org.thinking.sce.service.core.repository.dictionary;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.dictionary.EmployeePost;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface EmployeePostRepository extends DomainRepository<EmployeePost, Long> {
}