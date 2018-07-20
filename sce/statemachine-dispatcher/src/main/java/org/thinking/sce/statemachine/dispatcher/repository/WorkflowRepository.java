package org.thinking.sce.statemachine.dispatcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thinking.sce.statemachine.dispatcher.domain.Workflow;

@Repository
public interface WorkflowRepository extends JpaRepository<Workflow, String> {
}