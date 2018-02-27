package org.jointown.logistics.workflow.repository;

import org.springframework.statemachine.data.jpa.JpaStateMachineRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineRepository extends JpaStateMachineRepository {
}