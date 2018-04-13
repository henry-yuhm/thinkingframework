package org.thinking.logistics.workflow.repository;

import org.springframework.statemachine.data.jpa.JpaTransitionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransitionRepository extends JpaTransitionRepository {
}