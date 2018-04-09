package org.thinking.logistics.workflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thinking.logistics.workflow.entity.Monitor;
import org.thinking.logistics.workflow.repository.MonitorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonitorService {
    @Autowired
    private MonitorRepository monitorRepository;

    public List<Monitor> findAll() {
        return this.monitorRepository.findAll();
    }

    public List<String> getAll() {
        return this.monitorRepository.findAll().stream().map(Monitor::getMachineId).collect(Collectors.toList());
    }
}