package org.thinking.logistics.workflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.data.jpa.JpaRepositoryStateMachine;
import org.springframework.stereotype.Service;
import org.thinking.logistics.workflow.entity.Workflow;
import org.thinking.logistics.workflow.repository.MachineRepository;
import org.thinking.logistics.workflow.repository.WorkflowRepository;

@Service
public class InstanceService {
    private WorkflowRepository workflowRepository;

    private MachineRepository machineRepository;

    @Autowired
    public InstanceService(WorkflowRepository workflowRepository, MachineRepository machineRepository) {
        this.workflowRepository = workflowRepository;
        this.machineRepository = machineRepository;
    }

    public Workflow findOne(String machineId, String instanceId) {
        Workflow workflow = this.workflowRepository.getOne(machineId);

        JpaRepositoryStateMachine jpaRepositoryStateMachine = this.machineRepository.findById(instanceId).get();

        workflow.getNodes().forEach(node -> {
            String s = "";

            if (jpaRepositoryStateMachine != null) {
                s = jpaRepositoryStateMachine.getState();
            }

            node.setCurrent(node.getState().getState().equalsIgnoreCase(s));
        });

        return workflow;
    }
}