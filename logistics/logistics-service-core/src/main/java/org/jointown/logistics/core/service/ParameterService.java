package org.jointown.logistics.core.service;

import org.jointown.logistics.core.entity.Warehouse;
import org.jointown.logistics.core.entity.parameter.EnumParameter;
import org.jointown.logistics.core.entity.parameter.Parameter;
import org.jointown.logistics.core.entity.parameter.SwitchParameter;
import org.jointown.logistics.core.repository.ParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class ParameterService {
    @Autowired
    private ParameterRepository<? extends Parameter> repository;

    private Map<String, Object> ranges = new LinkedHashMap<>();

    public String getValue(String number) {
        return this.getValue(null, number);
    }

    public String getValue(Warehouse warehouse, String number) {
        EnumParameter parameter = (EnumParameter) this.repository.findByNumber(number);

        if (warehouse == null) {
            return parameter.getValue();
        }

        this.ranges.clear();
        parameter.getRanges().forEach(range -> this.ranges.put(range.getWarehouse(), range.getValue()));

        if (this.ranges.containsKey(warehouse.getName())) {
            return (String) this.ranges.get(warehouse.getName());
        } else {
            return parameter.getValue();
        }
    }

    public boolean isEnable(String number) {
        return this.isEnable(null, number);
    }

    public boolean isEnable(Warehouse warehouse, String number) {
        SwitchParameter parameter = (SwitchParameter) this.repository.findByNumber(number);

        if (warehouse == null) {
            return parameter.isEnable();
        }

        this.ranges.clear();
        parameter.getRanges().forEach(range -> this.ranges.put(range.getWarehouse(), range.isEnable()));

        if (this.ranges.containsKey(warehouse.getName())) {
            return (boolean) this.ranges.get(warehouse.getName());
        } else {
            return parameter.isEnable();
        }
    }
}