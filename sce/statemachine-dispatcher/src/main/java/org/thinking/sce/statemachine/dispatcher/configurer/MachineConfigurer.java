package org.thinking.sce.statemachine.dispatcher.configurer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineException;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.action.ActionListener;
import org.springframework.statemachine.action.Actions;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.ObjectStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineModelConfigurer;
import org.springframework.statemachine.config.model.ConfigurationData;
import org.springframework.statemachine.config.model.DefaultStateMachineModel;
import org.springframework.statemachine.config.model.StateMachineModelFactory;
import org.springframework.statemachine.config.model.StatesData;
import org.springframework.statemachine.config.model.TransitionsData;
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
import org.thinking.sce.statemachine.dispatcher.domain.Monitor;
import org.thinking.sce.statemachine.dispatcher.repository.ActionRepository;
import org.thinking.sce.statemachine.dispatcher.repository.GuardRepository;
import org.thinking.sce.statemachine.dispatcher.repository.MachineRepository;
import org.thinking.sce.statemachine.dispatcher.repository.MonitorRepository;
import org.thinking.sce.statemachine.dispatcher.repository.StateRepository;
import org.thinking.sce.statemachine.dispatcher.repository.TransitionRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Configuration
@EnableStateMachineFactory
@Slf4j
public class MachineConfigurer extends StateMachineConfigurerAdapter<String, String> {
    private RestTemplate restTemplate;

    private StateRepository stateRepository;

    private TransitionRepository transitionRepository;

    private ActionRepository actionRepository;

    private GuardRepository guardRepository;

    private MachineRepository machineRepository;

    private MonitorRepository monitorRepository;

    @Autowired
    public MachineConfigurer(RestTemplate restTemplate, StateRepository stateRepository, TransitionRepository transitionRepository, ActionRepository actionRepository, GuardRepository guardRepository, MachineRepository machineRepository, MonitorRepository monitorRepository) {
        this.restTemplate = restTemplate;
        this.stateRepository = stateRepository;
        this.transitionRepository = transitionRepository;
        this.actionRepository = actionRepository;
        this.guardRepository = guardRepository;
        this.machineRepository = machineRepository;
        this.monitorRepository = monitorRepository;
    }

    @Override
    public void configure(StateMachineModelConfigurer<String, String> model) throws Exception {
        model.withModel().factory(this.stateMachineModelFactory());
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<String, String> config) throws Exception {
        config.withMonitoring().monitor(this.stateMachineMonitor());
        config.withPersistence().runtimePersister(this.stateMachineRuntimePersister());
    }

    public Action<String, String> restAction(String spel) {
        return stateContext -> {
            try {
                stateContext.getExtendedState().getVariables().putAll(Optional.ofNullable(this.restTemplate.postForObject(spel, stateContext.getExtendedState().getVariables(), Map.class)).orElse(new HashMap(16)));
            } catch (Exception ex) {
                throw new RuntimeException(ex.getMessage(), ex.getCause());
            }
        };
    }

    public Action<String, String> errorAction() {
        return stateContext -> {
            throw new StateMachineException(stateContext.getException().getMessage(), stateContext.getException());
        };
    }

    @Bean
    public Map<String, JpaRepositoryAction> actions() {
        Map<String, JpaRepositoryAction> actions = new LinkedHashMap<>(16);

        this.actionRepository.findAll().forEach(action -> actions.put(Optional.ofNullable(action.getName()).orElse(action.getSpel()), action));

        return actions;
    }

    @Bean
    public Map<String, JpaRepositoryGuard> guards() {
        Map<String, JpaRepositoryGuard> guards = new LinkedHashMap<>(16);

        this.guardRepository.findAll().forEach(guard -> guards.put(guard.getName(), guard));

        return guards;
    }

//    @Bean
//    public StateMachineComponentResolver<String, String> stateMachineComponentResolver() {
//        Map<String, Action<String, String>> registeredActions = new HashMap<>(16);
//        Map<String, Guard<String, String>> registeredGuards = new HashMap<>(16);
//
//        this.actions().values().forEach(action -> {
//            if (!action.getName().isEmpty()) {
//                registeredActions.put(action.getName(), Actions.errorCallingAction(this.restAction(action.getSpel()), this.errorAction()));
//            }
//        });
//
//        this.guards().values().forEach(guard -> {
//            if (!guard.getName().isEmpty()) {
//                registeredGuards.put(guard.getName(), new SpelExpressionGuard<>(new SpelExpressionParser().parseExpression(guard.getSpel())));
//            }
//        });
//
//        return new DefaultStateMachineComponentResolver<>(registeredActions, registeredGuards);
//    }

    @Bean
    public StateMachineModelFactory<String, String> stateMachineModelFactory() {
        RepositoryStateMachineModelFactory factory = new RepositoryStateMachineModelFactory(this.stateRepository, this.transitionRepository);

        //        factory.setStateMachineComponentResolver(this.stateMachineComponentResolver());

        this.actions().values().forEach(action -> {
            if (!(Optional.ofNullable(action.getName()).orElse("")).isEmpty()) {
                factory.registerAction(action.getName(), Actions.errorCallingAction(this.restAction(action.getSpel()), this.errorAction()));
            }
        });

        this.guards().values().forEach(guard -> {
            if (!guard.getName().isEmpty()) {
                factory.registerGuard(guard.getName(), new SpelExpressionGuard<>(new SpelExpressionParser().parseExpression(guard.getSpel())));
            }
        });

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
                    log.info("状态从【" + from.getId() + "】改变到【" + to.getId() + "】");
                }
            }

            @Override
            public void stateMachineStarted(StateMachine<String, String> stateMachine) {
                log.info("当前的状态机是【" + stateMachine.getId() + "】");
            }

            @Override
            public void stateContext(StateContext<String, String> stateContext) {
                if (stateContext.getStage() == StateContext.Stage.TRANSITION_END) {
                    Monitor monitor = new Monitor();
                    monitor.setMachineId(stateContext.getStateMachine().getId());
                    monitor.setState(stateContext.getStateMachine().getState().getId());
                    monitorRepository.save(monitor);
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
        return new DefaultStateMachineService<>(this.stateMachineFactory(), this.stateMachineRuntimePersister());
    }
}