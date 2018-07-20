package org.thinking.sce.service.core.domain;

import lombok.Data;
import org.thinking.sce.service.core.domain.common.Warehouse;
import org.thinking.sce.service.core.domain.employee.Employee;
import org.thinking.sce.service.core.service.ParameterService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.regex.Pattern;

@Data
public abstract class BusinessBase {
    private Employee operator;//操作员

    @Resource
    private ParameterService parameterService;

    public BusinessBase() {
        this(null);
    }

    public BusinessBase(Employee operator) {
        this.operator = operator;
    }

    public final String getStringParameter(String name) throws Exception {
        return this.getStringParameter(null, name);
    }

    public final String getStringParameter(Warehouse warehouse, String name) throws Exception {
        return this.parameterService.getValue(warehouse, name);
    }

    public final int getIntegerParameter(String name) throws Exception {
        return this.getIntegerParameter(null, name);
    }

    public final int getIntegerParameter(Warehouse warehouse, String name) throws Exception {
        return this.getDecimalParameter(warehouse, name).intValue();
    }

    public final BigDecimal getDecimalParameter(String name) throws Exception {
        return this.getDecimalParameter(null, name);
    }

    public final BigDecimal getDecimalParameter(Warehouse warehouse, String name) throws Exception {
        Pattern pattern = Pattern.compile("[0-9]*");
        String value = this.parameterService.getValue(warehouse, name);
        return pattern.matcher(value).matches() ? new BigDecimal(value) : new BigDecimal(-1);
    }

    public final Instant getInstantParameter(String name) throws Exception {
        return this.getInstantParameter(null, name);
    }

    public final Instant getInstantParameter(Warehouse warehouse, String name) throws Exception {
        return Instant.parse(this.getStringParameter(warehouse, name));
    }

    public final boolean isEnable(String name) throws Exception {
        return this.isEnable(null, name);
    }

    public final boolean isEnable(Warehouse warehouse, String name) throws Exception {
        return Boolean.parseBoolean(this.getStringParameter(warehouse, name));
    }
}