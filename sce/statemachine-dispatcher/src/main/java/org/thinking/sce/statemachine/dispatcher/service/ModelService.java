package org.thinking.sce.statemachine.dispatcher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.statemachine.data.jpa.JpaRepositoryAction;
import org.springframework.statemachine.data.jpa.JpaRepositoryGuard;
import org.springframework.statemachine.data.jpa.JpaRepositoryState;
import org.springframework.statemachine.data.jpa.JpaRepositoryTransition;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thinking.sce.statemachine.dispatcher.domain.Node;
import org.thinking.sce.statemachine.dispatcher.domain.Workflow;
import org.thinking.sce.statemachine.dispatcher.repository.LineRepository;
import org.thinking.sce.statemachine.dispatcher.repository.NodeRepository;
import org.thinking.sce.statemachine.dispatcher.repository.StateRepository;
import org.thinking.sce.statemachine.dispatcher.repository.TransitionRepository;
import org.thinking.sce.statemachine.dispatcher.repository.WorkflowRepository;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ModelService {
    private WorkflowRepository workflowRepository;

    private NodeRepository nodeRepository;

    private LineRepository lineRepository;

    private StateRepository stateRepository;

    private TransitionRepository transitionRepository;

    private Map<String, JpaRepositoryAction> actions;

    private Map<String, JpaRepositoryGuard> guards;

    private MachineService machineService;

    @Autowired
    public ModelService(WorkflowRepository workflowRepository, NodeRepository nodeRepository, LineRepository lineRepository, StateRepository stateRepository, TransitionRepository transitionRepository, Map<String, JpaRepositoryAction> actions, Map<String, JpaRepositoryGuard> guards, MachineService machineService) {
        this.workflowRepository = workflowRepository;
        this.nodeRepository = nodeRepository;
        this.lineRepository = lineRepository;
        this.stateRepository = stateRepository;
        this.transitionRepository = transitionRepository;
        this.actions = actions;
        this.guards = guards;
        this.machineService = machineService;
    }

    private Set<JpaRepositoryAction> acquireActions(Set<JpaRepositoryAction> actions) {
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
        return this.workflowRepository.findById(id).get();
    }

    public List<Workflow> findAll() {
        return this.workflowRepository.findAll(Sort.by("id"));
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(Workflow workflow) throws Exception {
        if (workflow.getId() == null || workflow.getId().isEmpty()) {
            throw new Exception("工作流模板未定义ID");
        }

        if (workflow.getName() == null || workflow.getName().isEmpty()) {
            throw new Exception("工作流模板未定义名称");
        }

        if (workflow.getNodes().stream().map(Node::getState).map(JpaRepositoryState::getState).collect(Collectors.toSet()).size() < workflow.getNodes().size()) {
            throw new Exception("工作流模板不允许出现重复的节点状态值");
        }

        Map<String, Node> nodes = new LinkedHashMap<>(16);
        Map<String, JpaRepositoryState> states = new LinkedHashMap<>(16);
        Map<String, JpaRepositoryState> existingStates = new LinkedHashMap<>(16);
        Map<String, JpaRepositoryTransition> existingTransitions = new LinkedHashMap<>(16);

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
                existingState.setStateActions(this.acquireActions(node.getState().getStateActions()));
                existingState.setEntryActions(this.acquireActions(node.getState().getEntryActions()));
                existingState.setExitActions(this.acquireActions(node.getState().getExitActions()));
                existingState.setDeferredEvents(node.getState().getDeferredEvents());

                node.setState(existingState);
            } else {
                if (node.getState().getInitialAction() == null) {
                    node.getState().setInitialAction(null);
                } else {
                    node.getState().setInitialAction(this.actions.get(node.getState().getInitialAction().getName()));
                }
                node.getState().setStateActions(this.acquireActions(node.getState().getStateActions()));
                node.getState().setEntryActions(this.acquireActions(node.getState().getEntryActions()));
                node.getState().setExitActions(this.acquireActions(node.getState().getExitActions()));
            }

            this.stateRepository.save(node.getState());
        });

        this.nodeRepository.saveAll(workflow.getNodes());

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
                existingTransition.setActions(this.acquireActions(line.getTransition().getActions()));
                if (line.getTransition().getGuard() == null) {
                    existingTransition.setGuard(null);
                } else {
                    existingTransition.setGuard(this.guards.get(line.getTransition().getGuard().getName()));
                }

                line.setTransition(existingTransition);
            } else {
                line.getTransition().setActions(this.acquireActions(line.getTransition().getActions()));
                if (line.getTransition().getGuard() == null) {
                    line.getTransition().setGuard(null);
                } else {
                    line.getTransition().setGuard(this.guards.get(line.getTransition().getGuard().getName()));
                }
            }

            this.transitionRepository.save(line.getTransition());
        });

        this.lineRepository.saveAll(workflow.getLines());

        this.workflowRepository.save(workflow);

        this.machineService.register(workflow.getId());
    }
}