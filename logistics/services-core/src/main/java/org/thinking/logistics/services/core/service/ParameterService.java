package org.thinking.logistics.services.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thinking.logistics.services.core.entity.Warehouse;
import org.thinking.logistics.services.core.entity.parameter.Parameter;
import org.thinking.logistics.services.core.repository.ParameterRepository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ParameterService {
    private final Map<String, Parameter> parameters = new LinkedHashMap<>();

    private ParameterRepository repository;

    @Autowired
    public ParameterService(ParameterRepository repository) {
        this.repository = repository;
    }

    private Parameter acquireParameter(String no) {
        synchronized (this.parameters) {
            Parameter parameter = this.parameters.get(no);

            if (parameter == null) {
                parameter = this.repository.findByNo(no);
                this.parameters.put(no, parameter);
            }

            return parameter;
        }
    }

    public String getValue(String no) {
        return this.getValue(null, no);
    }

    public String getValue(Warehouse warehouse, String no) {
        Parameter parameter = this.acquireParameter(no);

        return warehouse == null ? parameter.getValue() : (Optional.of(parameter.getRanges().stream().filter(r -> r.getWarehouse().equalsIgnoreCase(warehouse.getName())).findFirst().get().getValue()).orElse(parameter.getValue()));
    }
}