package org.thinking.logistics.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.workflow.entity.Workflow;

@Repository
public interface WorkflowRepository extends JpaRepository<Workflow, String> {
}