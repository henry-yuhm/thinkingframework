package org.thinking.sce.service.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thinking.sce.service.core.domain.CompositeException;
import org.thinking.sce.service.core.domain.common.Warehouse;
import org.thinking.sce.service.core.domain.parameter.Parameter;
import org.thinking.sce.service.core.domain.parameter.QParameter;
import org.thinking.sce.service.core.repository.DomainRepository;

import javax.persistence.EntityManager;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ParameterService extends DomainService<QParameter, Parameter, Long> {
    private final Map<String, Parameter> parameters = new LinkedHashMap<>(16);

    @Autowired
    public ParameterService(EntityManager entityManager, DomainRepository<Parameter, Long> repository) {
        super(entityManager, repository, QParameter.parameter);
    }

    private Parameter acquire(String name, boolean verifiable) throws Exception {
        synchronized (this.parameters) {
            Parameter parameter = this.parameters.get(name);

            if (parameter == null) {
                parameter = this.getFactory().selectFrom(this.getPath())
                    .where(this.getPath().name.eq(name))
                    .fetchOne();

                if (verifiable) {
                    if (parameter == null) {
                        throw CompositeException.getException("无此名称【" + name + "】的参数定义");
                    }
                }

                this.parameters.put(name, parameter);
            }

            return parameter;
        }
    }

    public final String getValue(String name) throws Exception {
        return this.getValue(name, false);
    }

    public final String getValue(Warehouse warehouse, String name) throws Exception {
        return this.getValue(warehouse, name, false);
    }

    public final String getValue(String name, boolean verifiable) throws Exception {
        return this.getValue(null, name, verifiable);
    }

    public final String getValue(Warehouse warehouse, String name, boolean verifiable) throws Exception {
        Parameter parameter = this.acquire(name, verifiable);

        return warehouse == null ? parameter.getValue() : (Optional.of(parameter.getRanges().stream().filter(r -> r.getWarehouse().equalsIgnoreCase(warehouse.getName())).findFirst().get().getValue()).orElse(parameter.getValue()));
    }
}