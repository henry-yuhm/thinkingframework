package org.thinking.sce.statemachine.dispatcher.repository;

import org.springframework.statemachine.data.jpa.JpaGuardRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuardRepository extends JpaGuardRepository {
}