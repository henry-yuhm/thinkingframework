package org.thinking.sce.statemachine.dispatcher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.data.jpa.JpaRepositoryGuard;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thinking.sce.service.core.domain.CompositeException;
import org.thinking.sce.statemachine.dispatcher.repository.GuardRepository;

import java.util.List;
import java.util.Map;

@Service
public class GuardService {
    private GuardRepository guardRepository;

    private Map<String, JpaRepositoryGuard> guards;

    @Autowired
    public GuardService(GuardRepository guardRepository, Map<String, JpaRepositoryGuard> guards) {
        this.guardRepository = guardRepository;
        this.guards = guards;
    }

    public List<JpaRepositoryGuard> findAll() {
        return (List<JpaRepositoryGuard>) this.guardRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(List<JpaRepositoryGuard> guards) throws Exception {
        for (JpaRepositoryGuard guard : guards) {
            if (guard.getSpel().isEmpty()) {
                throw CompositeException.getException("看守【" + guard.getName() + "】的表达式不允许为空");
            }

            if (this.guards.containsKey(guard.getName())) {
                this.guards.get(guard.getName()).setSpel(guard.getSpel());
            } else {
                this.guards.put(guard.getName(), guard);
            }

            this.guardRepository.save(this.guards.get(guard.getName()));
        }

        this.guardRepository.saveAll(this.guards.values());
    }
}