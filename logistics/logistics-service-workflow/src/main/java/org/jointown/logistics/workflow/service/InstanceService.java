package org.jointown.logistics.workflow.service;

import org.jointown.logistics.workflow.entity.Workflow;
import org.jointown.logistics.workflow.repository.MachineRepository;
import org.jointown.logistics.workflow.repository.WorkflowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.data.jpa.JpaRepositoryStateMachine;
import org.springframework.stereotype.Service;

@Service
public class InstanceService {
    @Autowired
    private WorkflowRepository workflowRepository;

    @Autowired
    private MachineRepository machineRepository;

    public Workflow findOne(String machineId, String instanceId) {
        Workflow workflow = this.workflowRepository.findOne(machineId);

        JpaRepositoryStateMachine jpaRepositoryStateMachine = this.machineRepository.findOne(instanceId);

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