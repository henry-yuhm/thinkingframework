package org.thinking.logistics.statemachine.dispatcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.statemachine.dispatcher.domain.Workflow;

@Repository
public interface WorkflowRepository extends JpaRepository<Workflow, String> {
}