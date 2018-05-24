package org.thinking.logistics.services.core.repository.command;

import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.command.PilerCommand;
import org.thinking.logistics.services.core.repository.DomainRepository;

@Repository
public interface PilerCommandRepository extends DomainRepository<PilerCommand, Long> {
}