package org.thinking.logistics.core.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.thinking.logistics.core.entity.Employee;
import org.thinking.logistics.core.entity.Owner;
import org.thinking.logistics.core.entity.Warehouse;
import org.thinking.logistics.core.repository.EmployeeRepository;
import org.thinking.logistics.core.service.ParameterService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.regex.Pattern;

@Data
@NoArgsConstructor
public abstract class BusinessAdapter {
    protected Employee operator;//操作员

    @Resource
    private EmployeeRepository employeeRepository;

    @Resource
    private ParameterService parameterService;

    public BusinessAdapter(Employee operator) {
        this.operator = operator;
    }

    protected final Employee getEmployee(Owner owner, String number) {
        return this.employeeRepository.findByOwnerAndNumber(owner, number);
    }

    protected final String getStringParameter(String number) {
        return this.getStringParameter(null, number);
    }

    protected final String getStringParameter(Warehouse warehouse, String number) {
        return this.parameterService.getValue(warehouse, number);
    }

    protected final int getIntegerParameter(String number) {
        return this.getIntegerParameter(null, number);
    }

    protected final int getIntegerParameter(Warehouse warehouse, String number) {
        return this.getDecimalParameter(warehouse, number).intValue();
    }

    protected final BigDecimal getDecimalParameter(String number) {
        return this.getDecimalParameter(null, number);
    }

    protected final BigDecimal getDecimalParameter(Warehouse warehouse, String number) {
        Pattern pattern = Pattern.compile("[0-9]*");
        String value = this.parameterService.getValue(warehouse, number);
        return pattern.matcher(value).matches() ? new BigDecimal(value) : new BigDecimal(-1);
    }

    protected final Date getDateParameter(String number) {
        return this.getDateParameter(null, number);
    }

    protected final Date getDateParameter(Warehouse warehouse, String number) {
        return Date.valueOf(this.getStringParameter(warehouse, number));
    }

    protected final boolean isEnable(String number) {
        return this.isEnable(null, number);
    }

    protected final boolean isEnable(Warehouse warehouse, String number) {
        return Boolean.parseBoolean(this.getStringParameter(warehouse, number));
    }
}