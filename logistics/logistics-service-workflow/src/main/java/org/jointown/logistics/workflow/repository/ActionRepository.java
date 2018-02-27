package org.jointown.logistics.workflow.repository;

import org.springframework.statemachine.data.jpa.JpaActionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionRepository extends JpaActionRepository {
}