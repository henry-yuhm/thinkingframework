package org.jointown.logistics.workflow.configurer;

import org.jointown.logistics.workflow.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
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
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@EnableStateMachineFactory
public class MachineConfigurer extends StateMachineConfigurerAdapter<String, String> {
    @Autowired
    private RedisConfigurer redisConfigurer;

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

//    @Autowired
//    private StateMachineModelFactory<String, String> stateMachineModelFactory;

    @Override
    public void configure(StateMachineModelConfigurer<String, String> model) throws Exception {
        model.withModel().factory(this.stateMachineModelFactory());
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<String, String> config) throws Exception {
        config.withPersistence().runtimePersister(this.stateMachineRuntimePersister());
    }

    public void registerAction(Collection<JpaRepositoryAction> actions) {
        actions.forEach(action -> ((RepositoryStateMachineModelFactory) this.stateMachineModelFactory()).registerAction(action.getName(), Actions.errorCallingAction(context -> context.getExtendedState().getVariables().putAll(this.restTemplate().postForObject(action.getSpel(), context.getExtendedState().getVariables(), Map.class)), context -> System.out.println("操作执行错误:" + context.getException().getMessage()))));
    }

    private void registerGuard(Collection<JpaRepositoryGuard> guards) {
        guards.forEach(guard -> ((RepositoryStateMachineModelFactory) this.stateMachineModelFactory()).registerGuard(guard.getName(), new SpelExpressionGuard<>(new SpelExpressionParser().parseExpression(guard.getSpel()))));
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(this.redisConfigurer.getHost());
        jedisConnectionFactory.setPort(this.redisConfigurer.getPort());
        jedisConnectionFactory.setDatabase(this.redisConfigurer.getDatabase());
        return jedisConnectionFactory;
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
    public StateMachineModelFactory<String, String> stateMachineModelFactory() {
        return new RepositoryStateMachineModelFactory(this.stateRepository, this.transitionRepository);
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
    public StateMachineListener<String, String> stateMachineListener() {
        return new StateMachineListenerAdapter<String, String>() {
            @Override
            public void stateChanged(State<String, String> from, State<String, String> to) {
                if (from != null || to != null) {
                    System.out.println("状态从【" + from.getId() + "】改变到【" + to.getId() + "】");
                }
            }

            @Override
            public void stateMachineStarted(StateMachine<String, String> stateMachine) {
                System.out.println("当前的状态机是【" + stateMachine.getId() + "】");
            }

//            @Override
//            public void stateMachineStopped(StateMachine<String, String> stateMachine) {
//                if (stateMachine.isComplete()) {
//                    MachineConfigurer.this.stateMachineService().releaseStateMachine(stateMachine.getId());
//                }
//            }
        };
    }

    @Bean
    public ActionListener<String, String> actionListener() {
        return (stateMachine, action, duration) -> {
            System.out.println("状态机【" + stateMachine.getId() + "】执行操作耗时" + duration);
//            stateMachine.getExtendedState().getVariables().keySet().forEach(key -> System.out.println(stateMachine.getExtendedState().getVariables().get(key)));
        };
    }

    @Bean
    public StateMachineRuntimePersister<String, String, String> stateMachineRuntimePersister() {
        return new JpaPersistingStateMachineInterceptor<>(this.machineRepository);
    }

    @Bean
    public StateMachinePersister<String, String, String> stateMachinePersister() {
//        return new RedisStateMachinePersister<>(new RepositoryStateMachinePersist<>(new RedisStateMachineContextRepository<>(this.redisConnectionFactory())));
        return new DefaultStateMachinePersister<>(this.stateMachineRuntimePersister());
    }

    @Bean
    public StateMachineService<String, String> stateMachineService() {
        this.registerAction(this.actions().values());
        this.registerGuard(this.guards().values());

        StateMachineService<String, String> stateMachineService = new DefaultStateMachineService<>(this.stateMachineFactory(), this.stateMachineRuntimePersister());

        this.workflowRepository.findAll().forEach(workflow -> {
            StateMachine<String, String> stateMachine = stateMachineService.acquireStateMachine(workflow.getId(), false);
            stateMachine.addStateListener(this.stateMachineListener());
            stateMachine.getStates().forEach(state -> state.addActionListener(this.actionListener()));
            stateMachine.getTransitions().forEach(transition -> transition.addActionListener(this.actionListener()));
            stateMachine.getStateMachineAccessor().withAllRegions().forEach(stateMachineAccess -> stateMachineAccess.addStateMachineInterceptor(this.stateMachineInterceptor()));
        });

        return stateMachineService;
    }

//    @Bean
//    public StateMachineJackson2RepositoryPopulatorFactoryBean stateMachineJackson2RepositoryPopulatorFactoryBean() {
//        StateMachineJackson2RepositoryPopulatorFactoryBean factoryBean = new StateMachineJackson2RepositoryPopulatorFactoryBean();
//        factoryBean.setResources(new Resource[]{new ClassPathResource("data.json")});
//        return factoryBean;
//    }

//    @Bean
//    public CommonsPool2TargetSource commonsPool2TargetSource(){
//        CommonsPool2TargetSource commonsPool2TargetSource = new CommonsPool2TargetSource();
//        commonsPool2TargetSource.setMaxSize(3);
//        commonsPool2TargetSource.setTargetBeanName("stateMachineFactory");
//        return commonsPool2TargetSource;
//    }
//
//    @Bean
//    @Scope(value = "request",proxyMode = ScopedProxyMode.INTERFACES)
//    public ProxyFactoryBean proxyFactoryBean(){
//        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
//        proxyFactoryBean.setTargetSource(this.commonsPool2TargetSource());
//        return proxyFactoryBean;
//    }
}