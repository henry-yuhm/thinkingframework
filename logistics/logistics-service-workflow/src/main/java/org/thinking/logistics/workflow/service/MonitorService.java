package org.thinking.logistics.workflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thinking.logistics.workflow.entity.Monitor;
import org.thinking.logistics.workflow.repository.MonitorRepository;

import java.util.List;

@Service
public class MonitorService {
    @Autowired
    private MonitorRepository monitorRepository;

    public List<Monitor> findAll() {
        return this.monitorRepository.findAll();
    }
}