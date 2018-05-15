package org.thinking.logistics.services.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thinking.logistics.services.core.domain.CompositeException;
import org.thinking.logistics.services.core.domain.Warehouse;
import org.thinking.logistics.services.core.domain.parameter.Parameter;
import org.thinking.logistics.services.core.domain.parameter.QParameter;
import org.thinking.logistics.services.core.repository.DomainRepository;

import javax.persistence.EntityManager;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ParameterService extends DomainService<QParameter, Parameter, Long> {
    private final Map<String, Parameter> parameters = new LinkedHashMap<>();

    @Autowired
    public ParameterService(EntityManager entityManager, DomainRepository<Parameter, Long> repository) {
        super(entityManager, repository, QParameter.parameter);
    }

    private Parameter acquire(String no, boolean verifiable) throws Exception {
        synchronized (this.parameters) {
            Parameter parameter = this.parameters.get(no);

            if (parameter == null) {
                parameter = this.getFactory().selectFrom(this.getPath())
                    .where(this.getPath().no.eq(no))
                    .fetchOne();

                if (verifiable) {
                    if (parameter == null) {
                        throw CompositeException.getException("无此编号【" + no + "】的参数定义");
                    }
                }

                this.parameters.put(no, parameter);
            }

            return parameter;
        }
    }

    public final String getValue(String no) throws Exception {
        return this.getValue(no, false);
    }

    public final String getValue(Warehouse warehouse, String no) throws Exception {
        return this.getValue(warehouse, no, false);
    }

    public final String getValue(String no, boolean verifiable) throws Exception {
        return this.getValue(null, no, verifiable);
    }

    public final String getValue(Warehouse warehouse, String no, boolean verifiable) throws Exception {
        Parameter parameter = this.acquire(no, verifiable);

        return warehouse == null ? parameter.getValue() : (Optional.of(parameter.getRanges().stream().filter(r -> r.getWarehouse().equalsIgnoreCase(warehouse.getName())).findFirst().get().getValue()).orElse(parameter.getValue()));
    }
}