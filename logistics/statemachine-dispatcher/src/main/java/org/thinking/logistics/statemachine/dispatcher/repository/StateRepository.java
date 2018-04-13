package org.thinking.logistics.statemachine.dispatcher.repository;

import org.springframework.statemachine.data.jpa.JpaStateRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaStateRepository {
}