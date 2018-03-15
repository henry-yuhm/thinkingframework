package org.jointown.logistics.workflow.service;

import org.jointown.logistics.workflow.entity.Monitor;
import org.jointown.logistics.workflow.repository.MonitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitorService {
    @Autowired
    private MonitorRepository monitorRepository;

    public List<Monitor> findAll() {
        return this.monitorRepository.findAll();
    }
}