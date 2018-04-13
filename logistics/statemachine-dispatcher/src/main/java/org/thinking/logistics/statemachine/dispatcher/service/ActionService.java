package org.thinking.logistics.statemachine.dispatcher.service;

import org.springframework.statemachine.action.Actions;
import org.springframework.statemachine.config.model.StateMachineModelFactory;
import org.springframework.statemachine.data.RepositoryStateMachineModelFactory;
import org.springframework.statemachine.data.jpa.JpaRepositoryAction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.thinking.logistics.services.core.domain.CompositeException;
import org.thinking.logistics.statemachine.dispatcher.configurer.MachineConfigurer;
import org.thinking.logistics.statemachine.dispatcher.repository.ActionRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ActionService {
    private ActionRepository actionRepository;

    private Map<String, JpaRepositoryAction> actions;

    private StateMachineModelFactory<String, String> stateMachineModelFactory;

    private MachineConfigurer machineConfigurer;

    public ActionService(ActionRepository actionRepository, Map<String, JpaRepositoryAction> actions, StateMachineModelFactory<String, String> stateMachineModelFactory, MachineConfigurer machineConfigurer) {
        this.actionRepository = actionRepository;
        this.actions = actions;
        this.stateMachineModelFactory = stateMachineModelFactory;
        this.machineConfigurer = machineConfigurer;
    }

    public List<JpaRepositoryAction> findAll() {
        return (List<JpaRepositoryAction>) this.actionRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(List<JpaRepositoryAction> actions) throws Exception {
        for (JpaRepositoryAction action : actions) {

            if (!StringUtils.hasText(action.getSpel())) {
                throw CompositeException.getException("操作的表达式不允许为空");
            }

            if (StringUtils.hasText(action.getName())) {
                if (this.actions.containsKey(action.getName())) {
                    this.actions.get(action.getName()).setSpel(action.getSpel());
                } else {
                    this.actions.put(action.getName(), action);

                    ((RepositoryStateMachineModelFactory) this.stateMachineModelFactory).registerAction(action.getName(), Actions.errorCallingAction(this.machineConfigurer.restAction(action.getSpel()), this.machineConfigurer.errorAction()));
                }
            } else if (StringUtils.hasText(action.getSpel())) {
                if (this.actions.containsKey(action.getSpel())) {
                    this.actions.get(action.getSpel()).setSpel(action.getSpel());
                } else {
                    this.actions.put(action.getSpel(), action);
                }
            }
        }

        List<String> values;

        values = this.actions.values().stream().filter(action -> action.getName() != null).map(JpaRepositoryAction::getName).collect(Collectors.toList());
        if (values.stream().distinct().count() < values.size()) {
            throw CompositeException.getException("操作的名称不能重复");
        }

        values = this.actions.values().stream().filter(action -> action.getSpel() != null).map(JpaRepositoryAction::getSpel).collect(Collectors.toList());
        if (values.stream().distinct().count() < values.size()) {
            throw CompositeException.getException("操作的表达式不能重复");
        }

        this.actionRepository.saveAll(this.actions.values());
    }
}