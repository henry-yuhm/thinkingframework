package org.thinking.logistics.workflow.repository;

import org.springframework.statemachine.data.jpa.JpaStateRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaStateRepository {
}