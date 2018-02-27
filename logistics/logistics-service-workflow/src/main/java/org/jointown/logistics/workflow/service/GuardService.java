package org.jointown.logistics.workflow.service;

import org.jointown.logistics.workflow.configurer.MachineConfigurer;
import org.jointown.logistics.workflow.repository.GuardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.data.jpa.JpaRepositoryGuard;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class GuardService {
    @Autowired
    private MachineConfigurer machineConfigurer;

    @Autowired
    private GuardRepository guardRepository;

    @Autowired
    private Map<String, JpaRepositoryGuard> guards;

    @Transactional(rollbackFor = Exception.class)
    public boolean save(List<JpaRepositoryGuard> guards) {
        try {
            guards.forEach(guard -> {
                if (this.guards.containsKey(guard.getName())) {
                    this.guards.get(guard.getName()).setSpel(guard.getSpel());
                } else {
                    this.guards.put(guard.getName(), guard);
                }

                this.guardRepository.save(this.guards.get(guard.getName()));

//                this.stateMachineConfigurer.registerGuard((RepositoryStateMachineModelFactory)this.stateMachineConfigurer.stateMachineModelFactory(), this.guards.get(guard.getName()));
            });

            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}