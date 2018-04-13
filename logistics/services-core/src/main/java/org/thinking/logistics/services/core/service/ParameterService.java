package org.thinking.logistics.services.core.service;

import org.springframework.stereotype.Service;
import org.thinking.logistics.services.core.entity.Parameter;
import org.thinking.logistics.services.core.entity.Warehouse;
import org.thinking.logistics.services.core.repository.ParameterRepository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ParameterService {
    private final Map<String, Parameter> parameters = new LinkedHashMap<>();

    private ParameterRepository repository;

    public ParameterService(ParameterRepository repository) {
        this.repository = repository;
    }

    private Parameter acquireParameter(String number) {
        synchronized (this.parameters) {
            Parameter parameter = this.parameters.get(number);

            if (parameter == null) {
                parameter = this.repository.findByNumber(number);
                this.parameters.put(number, parameter);
            }

            return parameter;
        }
    }

    public String getValue(String number) {
        return this.getValue(null, number);
    }

    public String getValue(Warehouse warehouse, String number) {
        Parameter parameter = this.acquireParameter(number);

        return warehouse == null ? parameter.getValue() : (Optional.of(parameter.getRanges().stream().filter(r -> r.getWarehouse().equalsIgnoreCase(warehouse.getName())).findFirst().get().getValue()).orElse(parameter.getValue()));
    }
}