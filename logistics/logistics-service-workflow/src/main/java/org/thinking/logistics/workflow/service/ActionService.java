package org.thinking.logistics.workflow.service;

import org.springframework.statemachine.data.jpa.JpaRepositoryAction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thinking.logistics.workflow.repository.ActionRepository;

import java.util.List;
import java.util.Map;

@Service
public class ActionService {
    private ActionRepository actionRepository;

    private Map<String, JpaRepositoryAction> actions;

    public ActionService(ActionRepository actionRepository, Map<String, JpaRepositoryAction> actions) {
        this.actionRepository = actionRepository;
        this.actions = actions;
    }

    public List<JpaRepositoryAction> findAll() {
        return (List<JpaRepositoryAction>) this.actionRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean save(List<JpaRepositoryAction> actions) {
        try {
            actions.forEach(action -> {
                if (this.actions.containsKey(action.getName())) {
                    this.actions.get(action.getName()).setSpel(action.getSpel());
                } else {
                    this.actions.put(action.getName(), action);
                }

                this.actionRepository.save(this.actions.get(action.getName()));
            });

            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}