package org.thinking.logistics.workflow.repository;

import org.springframework.statemachine.data.jpa.JpaActionRepository;
import org.springframework.statemachine.data.jpa.JpaRepositoryAction;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionRepository extends JpaActionRepository {
    JpaRepositoryAction findByName(String name);

    JpaRepositoryAction findBySpel(String spel);
}