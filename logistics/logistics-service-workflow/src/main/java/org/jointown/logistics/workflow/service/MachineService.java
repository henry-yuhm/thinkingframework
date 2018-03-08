package org.jointown.logistics.workflow.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineException;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.service.StateMachineService;
import org.springframework.statemachine.support.AbstractStateMachine;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
public class MachineService {
    @Autowired
    private StateMachinePersister<String, String, String> stateMachinePersister;

    @Autowired
    private StateMachineService<String, String> stateMachineService;

    public void start(String machineId, String instanceId, String parameters) throws Exception {
        StateMachine<String, String> stateMachine = this.stateMachineService.acquireStateMachine(machineId, false);

        this.stateMachinePersister.restore(stateMachine, instanceId);

        if (stateMachine.getId() == null) {
            ((AbstractStateMachine) stateMachine).setId(instanceId);

            stateMachine.getExtendedState().getVariables().clear();
            stateMachine.getExtendedState().getVariables().putAll(parameters == null ? new LinkedHashMap<>() : JSONObject.parseObject(parameters).getInnerMap());

            stateMachine.start();

            this.stateMachinePersister.persist(stateMachine, instanceId);
        } else {
            throw new StateMachineException("状态机已经启动并正在运行");
        }
    }

    public void stop(String machineId, String instanceId) throws Exception {
        StateMachine<String, String> stateMachine = this.stateMachineService.acquireStateMachine(machineId);

        this.stateMachinePersister.restore(stateMachine, instanceId);

        if (stateMachine.getId() == null) {
            ((AbstractStateMachine) stateMachine).setId(instanceId);
        }

        stateMachine.stop();

        this.stateMachinePersister.persist(stateMachine, instanceId);
    }

    public void sendEvent(String machineId, String instanceId, String event) throws Exception {
        StateMachine<String, String> stateMachine = this.stateMachineService.acquireStateMachine(machineId, false);

        this.stateMachinePersister.restore(stateMachine, instanceId);

        if (stateMachine.getId() == null) {
            throw new StateMachineException("状态机不存在");
        }

        stateMachine.sendEvent(event);

        this.stateMachinePersister.persist(stateMachine, instanceId);
    }
}