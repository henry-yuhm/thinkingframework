package org.jointown.logistics.workflow.configurer;

import com.alibaba.fastjson.JSONObject;
import org.jointown.logistics.workflow.entity.Monitor;
import org.jointown.logistics.workflow.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.action.ActionListener;
import org.springframework.statemachine.action.Actions;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.ObjectStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineModelConfigurer;
import org.springframework.statemachine.config.model.*;
import org.springframework.statemachine.data.RepositoryStateMachineModelFactory;
import org.springframework.statemachine.data.jpa.JpaPersistingStateMachineInterceptor;
import org.springframework.statemachine.data.jpa.JpaRepositoryAction;
import org.springframework.statemachine.data.jpa.JpaRepositoryGuard;
import org.springframework.statemachine.guard.SpelExpressionGuard;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.monitor.CompositeStateMachineMonitor;
import org.springframework.statemachine.monitor.StateMachineMonitor;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;
import org.springframework.statemachine.service.DefaultStateMachineService;
import org.springframework.statemachine.service.StateMachineService;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptor;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@EnableStateMachineFactory
public class MachineConfigurer extends StateMachineConfigurerAdapter<String, String> {
    @Autowired
    private WorkflowRepository workflowRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private TransitionRepository transitionRepository;

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private GuardRepository guardRepository;

    @Autowired
    private MachineRepository machineRepository;

    @Autowired
    private MonitorRepository monitorRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void configure(StateMachineModelConfigurer<String, String> model) throws Exception {
        model.withModel().factory(this.stateMachineModelFactory());
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<String, String> config) throws Exception {
        config.withMonitoring().monitor(this.stateMachineMonitor());
        config.withPersistence().runtimePersister(this.stateMachineRuntimePersister());
    }

    private Action<String, String> restAction(String spel) {
        return stateContext -> stateContext.getExtendedState().getVariables().putAll(MachineConfigurer.this.restTemplate.postForObject(spel, stateContext.getExtendedState().getVariables(), Map.class));
    }

    private Action<String, String> errorAction() {
        return stateContext -> System.out.println("操作执行错误:" + stateContext.getException().getMessage());
    }

    @Bean
    public Map<String, JpaRepositoryAction> actions() {
        Map<String, JpaRepositoryAction> actions = new LinkedHashMap<>();

        this.actionRepository.findAll().forEach(action -> actions.put(action.getName(), action));

        return actions;
    }

    @Bean
    public Map<String, JpaRepositoryGuard> guards() {
        Map<String, JpaRepositoryGuard> guards = new LinkedHashMap<>();

        this.guardRepository.findAll().forEach(guard -> guards.put(guard.getName(), guard));

        return guards;
    }

    @Bean
    public StateMachineComponentResolver<String, String> stateMachineComponentResolver() {
        DefaultStateMachineComponentResolver<String, String> resolver = new DefaultStateMachineComponentResolver<>();

        this.actions().values().forEach(action -> {
            if (!action.getName().isEmpty()) {
                resolver.registerAction(action.getName(), Actions.errorCallingAction(this.restAction(action.getSpel()), this.errorAction()));
            }
        });

        this.guards().values().forEach(guard -> {
            if (!guard.getName().isEmpty()) {
                resolver.registerGuard(guard.getName(), new SpelExpressionGuard<>(new SpelExpressionParser().parseExpression(guard.getSpel())));
            }
        });

        return resolver;
    }

    @Bean
    public StateMachineModelFactory<String, String> stateMachineModelFactory() {
        RepositoryStateMachineModelFactory factory = new RepositoryStateMachineModelFactory(this.stateRepository, this.transitionRepository);
        factory.setStateMachineComponentResolver(this.stateMachineComponentResolver());
        return factory;
    }

    @Bean
    public StateMachineFactory<String, String> stateMachineFactory() {
        return new ObjectStateMachineFactory<>(new DefaultStateMachineModel<>(new ConfigurationData<>(), new StatesData<>(new ArrayList<>()), new TransitionsData<>(new ArrayList<>())), this.stateMachineModelFactory());
    }

    @Bean
    public StateMachineInterceptor<String, String> stateMachineInterceptor() {
        return new StateMachineInterceptorAdapter<String, String>() {
//            @Override
//            public Message<String> preEvent(Message<String> message, StateMachine<String, String> stateMachine) {
//                return null;
//            }

            @Override
            public void preStateChange(State<String, String> state, Message<String> message, Transition<String, String> transition, StateMachine<String, String> stateMachine) {
            }

            @Override
            public void postStateChange(State<String, String> state, Message<String> message, Transition<String, String> transition, StateMachine<String, String> stateMachine) {
            }

//            @Override
//            public StateContext<String, String> preTransition(StateContext<String, String> stateContext) {
//                return null;
//            }

//            @Override
//            public StateContext<String, String> postTransition(StateContext<String, String> stateContext) {
//                return null;
//            }

            @Override
            public Exception stateMachineError(StateMachine<String, String> stateMachine, Exception e) {
                return new Exception("状态机【" + stateMachine.getId() + "】运行错误:" + e.getMessage());
            }
        };
    }

    @Bean
    public StateMachineMonitor<String, String> stateMachineMonitor() {
        return new CompositeStateMachineMonitor<>();
    }

    @Bean
    public StateMachineListener<String, String> stateMachineListener() {
        return new StateMachineListenerAdapter<String, String>() {
            @Override
            public void stateChanged(State<String, String> from, State<String, String> to) {
                if (from != null) {
                    System.out.println("状态从【" + from.getId() + "】改变到【" + to.getId() + "】");
                }
            }

            @Override
            public void stateMachineStarted(StateMachine<String, String> stateMachine) {
                System.out.println("当前的状态机是【" + stateMachine.getId() + "】");
            }

            @Override
            public void stateContext(StateContext<String, String> stateContext) {
                if (stateContext.getStage() == StateContext.Stage.TRANSITION) {
                    Monitor monitor = new Monitor();

                    monitor.setMachineId(stateContext.getStateMachine().getId());
                    monitor.setStateContext(JSONObject.toJSONString(stateContext, true));

                    MachineConfigurer.this.monitorRepository.save(monitor);
                }
            }
        };
    }

    @Bean
    public ActionListener<String, String> actionListener() {
        return (stateMachine, action, duration) -> System.out.println("状态机【" + stateMachine.getId() + "】执行操作耗时" + duration);
    }

    @Bean
    public StateMachineRuntimePersister<String, String, String> stateMachineRuntimePersister() {
        return new JpaPersistingStateMachineInterceptor<>(this.machineRepository);
    }

    @Bean
    public StateMachinePersister<String, String, String> stateMachinePersister() {
        return new DefaultStateMachinePersister<>(this.stateMachineRuntimePersister());
    }

    @Bean
    public StateMachineService<String, String> stateMachineService() {
        StateMachineService<String, String> stateMachineService = new DefaultStateMachineService<>(this.stateMachineFactory(), this.stateMachineRuntimePersister());

        ((AbstractStateMachineModelFactory) this.stateMachineModelFactory()).setBeanFactory(null);

        this.workflowRepository.findAll().forEach(workflow -> {
            StateMachine<String, String> stateMachine = stateMachineService.acquireStateMachine(workflow.getId(), false);
            stateMachine.addStateListener(this.stateMachineListener());
            stateMachine.getStates().forEach(state -> state.addActionListener(this.actionListener()));
            stateMachine.getTransitions().forEach(transition -> transition.addActionListener(this.actionListener()));
            stateMachine.getStateMachineAccessor().withAllRegions().forEach(stateMachineAccess -> stateMachineAccess.addStateMachineInterceptor(this.stateMachineInterceptor()));
        });

        return stateMachineService;
    }
}