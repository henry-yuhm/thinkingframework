package org.jointown.logistics.workflow.service;

import org.jointown.logistics.workflow.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.data.jpa.JpaRepositoryAction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ActionService {
    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private Map<String, JpaRepositoryAction> actions;

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