package org.thinking.sce.statemachine.dispatcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thinking.sce.statemachine.dispatcher.domain.Monitor;

import java.util.List;

@Repository
public interface MonitorRepository extends JpaRepository<Monitor, Long> {
    List<Monitor> findAllByMachineId(String machineId);
}