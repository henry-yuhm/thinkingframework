package org.thinking.sce.statemachine.dispatcher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thinking.sce.statemachine.dispatcher.domain.Monitor;
import org.thinking.sce.statemachine.dispatcher.repository.MonitorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonitorService {
    private MonitorRepository monitorRepository;

    @Autowired
    public MonitorService(MonitorRepository monitorRepository) {
        this.monitorRepository = monitorRepository;
    }

    public List<Monitor> findAll() {
        return this.monitorRepository.findAll();
    }

    public List<String> getAll() {
        return this.monitorRepository.findAll().stream().map(Monitor::getMachineId).collect(Collectors.toList());
    }
}