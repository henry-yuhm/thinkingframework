package org.thinking.sce.statemachine.dispatcher.repository;

import org.springframework.statemachine.data.jpa.*;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionRepository extends JpaActionRepository {
    JpaRepositoryAction findByName(String name);

    JpaRepositoryAction findBySpel(String spel);
}