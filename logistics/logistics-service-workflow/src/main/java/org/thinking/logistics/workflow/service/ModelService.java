package org.thinking.logistics.workflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.data.jpa.JpaRepositoryAction;
import org.springframework.statemachine.data.jpa.JpaRepositoryGuard;
import org.springframework.statemachine.data.jpa.JpaRepositoryState;
import org.springframework.statemachine.data.jpa.JpaRepositoryTransition;
import org.springframework.statemachine.service.StateMachineService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thinking.logistics.workflow.entity.Node;
import org.thinking.logistics.workflow.entity.Workflow;
import org.thinking.logistics.workflow.repository.*;

import java.util.*;

@Service
public class ModelService {
    @Autowired
    private WorkflowRepository workflowRepository;

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private LineRepository lineRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private TransitionRepository transitionRepository;

    @Autowired
    private Map<String, JpaRepositoryAction> actions;

    @Autowired
    private Map<String, JpaRepositoryGuard> guards;

    @Autowired
    private StateMachineService<String, String> stateMachineService;

    private Set<JpaRepositoryAction> getActions(Set<JpaRepositoryAction> actions) {
        Set<JpaRepositoryAction> actionSet = new LinkedHashSet<>();

        if (actions != null && actions.size() > 0) {
            actions.forEach(action -> {
                if (this.actions.containsKey(action.getName())) {
                    actionSet.add(this.actions.get(action.getName()));
                }
            });
        }

        return actionSet;
    }

    public Workflow findOne(String id) {
        return this.workflowRepository.findOne(id);
    }

    public List<Workflow> findAll() {
        return this.workflowRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean save(Workflow workflow) {
        try {
            Map<String, Node> nodes = new LinkedHashMap<>();
            Map<String, JpaRepositoryState> states = new LinkedHashMap<>();
            Map<String, JpaRepositoryState> existingStates = new LinkedHashMap<>();
            Map<String, JpaRepositoryTransition> existingTransitions = new LinkedHashMap<>();

            this.stateRepository.findByMachineId(workflow.getId()).forEach(state -> existingStates.put(state.getState(), state));

            workflow.getNodes().forEach(node -> {
                if (existingStates.containsKey(node.getState().getState())) {
                    JpaRepositoryState existingState = existingStates.get(node.getState().getState());

                    existingState.setRegion(node.getState().getRegion());
                    existingState.setInitial(node.getState().isInitial());
                    existingState.setKind(node.getState().getKind());
                    existingState.setSubmachineId(node.getState().getSubmachineId());
                    if (node.getState().getInitialAction() == null) {
                        existingState.setInitialAction(null);
                    } else {
                        existingState.setInitialAction(this.actions.get(node.getState().getInitialAction().getName()));
                    }
                    existingState.setParentState(node.getState().getParentState());
                    existingState.setStateActions(this.getActions(node.getState().getStateActions()));
                    existingState.setEntryActions(this.getActions(node.getState().getEntryActions()));
                    existingState.setExitActions(this.getActions(node.getState().getExitActions()));
                    existingState.setDeferredEvents(node.getState().getDeferredEvents());

                    node.setState(existingState);
                } else {
                    if (node.getState().getInitialAction() == null) {
                        node.getState().setInitialAction(null);
                    } else {
                        node.getState().setInitialAction(this.actions.get(node.getState().getInitialAction().getName()));
                    }
                    node.getState().setStateActions(this.getActions(node.getState().getStateActions()));
                    node.getState().setEntryActions(this.getActions(node.getState().getEntryActions()));
                    node.getState().setExitActions(this.getActions(node.getState().getExitActions()));
                }

                this.stateRepository.save(node.getState());
            });

            this.nodeRepository.save(workflow.getNodes());

            workflow.getNodes().forEach(node -> {
                nodes.put(node.getId(), node);
                states.put(node.getState().getState(), node.getState());
            });

            this.transitionRepository.findByMachineId(workflow.getId()).forEach(transition -> existingTransitions.put(transition.getSource().getState() + "." + transition.getTarget().getState(), transition));

            workflow.getLines().forEach(line -> {
                line.getTransition().setSource(states.get(nodes.get(line.getSource()).getState().getState()));
                line.getTransition().setTarget(states.get(nodes.get(line.getTarget()).getState().getState()));

                String key;
                key = line.getTransition().getSource().getState() + "." + line.getTransition().getTarget().getState();
                if (existingTransitions.containsKey(key)) {
                    JpaRepositoryTransition existingTransition = existingTransitions.get(key);

                    existingTransition.setEvent(line.getTransition().getEvent());
                    existingTransition.setKind(line.getTransition().getKind());
                    existingTransition.setActions(this.getActions(line.getTransition().getActions()));
                    if (line.getTransition().getGuard() == null) {
                        existingTransition.setGuard(null);
                    } else {
                        existingTransition.setGuard(this.guards.get(line.getTransition().getGuard().getName()));
                    }

                    line.setTransition(existingTransition);
                } else {
                    line.getTransition().setActions(this.getActions(line.getTransition().getActions()));
                    if (line.getTransition().getGuard() == null) {
                        line.getTransition().setGuard(null);
                    } else {
                        line.getTransition().setGuard(this.guards.get(line.getTransition().getGuard().getName()));
                    }
                }

                this.transitionRepository.save(line.getTransition());
            });

            this.lineRepository.save(workflow.getLines());

            this.workflowRepository.save(workflow);

            this.stateMachineService.releaseStateMachine(workflow.getId(), true);
            this.stateMachineService.acquireStateMachine(workflow.getId());

            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteAll() {
        this.workflowRepository.deleteAllInBatch();
    }
}