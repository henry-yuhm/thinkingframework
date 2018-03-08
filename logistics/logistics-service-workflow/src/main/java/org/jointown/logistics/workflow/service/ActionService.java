package org.jointown.logistics.workflow.service;

import org.jointown.logistics.workflow.configurer.MachineConfigurer;
import org.jointown.logistics.workflow.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.statemachine.data.jpa.JpaRepositoryAction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class ActionService {
    @Autowired
    private MachineConfigurer machineConfigurer;

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private Map<String, JpaRepositoryAction> actions;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

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

            this.update(actions);

            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public void update(List<JpaRepositoryAction> actions) {
        this.machineConfigurer.registerAction(actions);

        List<ServiceInstance> serviceInstances = this.discoveryClient.getInstances("logistics-service-workflow");
        serviceInstances.forEach(serviceInstance -> {
//            if (!(serviceInstance.getServiceId().equalsIgnoreCase(this.applicationContext.getEnvironment().getProperty("eureka.instance.instance-id"))))
//                this.restTemplate.postForObject(serviceInstance.getUri().toString()+"/update", actions, void.class);
            System.out.println(serviceInstance.getServiceId() + ":" + serviceInstance.getHost() + ":" + serviceInstance.getPort());
        });
    }
}