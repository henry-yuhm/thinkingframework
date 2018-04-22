package org.thinking.logistics.services.core.repository.command;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.entity.command.OutboundCommand;

@Repository
public interface OutboundCommandRepository extends JpaRepository<OutboundCommand, Long> {
}