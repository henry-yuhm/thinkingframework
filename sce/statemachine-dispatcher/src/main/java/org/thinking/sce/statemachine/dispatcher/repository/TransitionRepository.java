package org.thinking.sce.statemachine.dispatcher.repository;

import org.springframework.statemachine.data.jpa.JpaTransitionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransitionRepository extends JpaTransitionRepository {
}