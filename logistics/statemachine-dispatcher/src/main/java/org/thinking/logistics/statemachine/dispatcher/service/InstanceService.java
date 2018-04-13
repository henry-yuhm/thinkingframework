package org.thinking.logistics.statemachine.dispatcher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thinking.logistics.statemachine.dispatcher.entity.Monitor;
import org.thinking.logistics.statemachine.dispatcher.entity.Workflow;
import org.thinking.logistics.statemachine.dispatcher.repository.MonitorRepository;
import org.thinking.logistics.statemachine.dispatcher.repository.WorkflowRepository;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class InstanceService {
    private WorkflowRepository workflowRepository;

    private MonitorRepository monitorRepository;

    @Autowired
    public InstanceService(WorkflowRepository workflowRepository, MonitorRepository monitorRepository) {
        this.workflowRepository = workflowRepository;
        this.monitorRepository = monitorRepository;
    }

    public Workflow findOne(String machineId, String instanceId) {
        Workflow workflow = this.workflowRepository.getOne(machineId);

        Map<String, String> map = new LinkedHashMap<>();
        this.monitorRepository.findAllByMachineId(instanceId).stream().map(Monitor::getState).forEach(state -> map.put(state, state));

        workflow.getNodes().forEach(node -> node.setCurrent(map.containsKey(node.getState().getState())));

        return workflow;
    }
}