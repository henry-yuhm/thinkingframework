package org.thinking.sce.service.core.repository.command;

import org.springframework.stereotype.Repository;
import org.thinking.sce.service.core.domain.command.PilerCommand;
import org.thinking.sce.service.core.repository.DomainRepository;

@Repository
public interface PilerCommandRepository extends DomainRepository<PilerCommand, Long> {
}