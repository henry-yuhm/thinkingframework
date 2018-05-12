package org.thinking.logistics.services.core.domain;

import lombok.Data;
import org.thinking.logistics.services.core.domain.employee.Employee;
import org.thinking.logistics.services.core.service.ParameterService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Date;
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

    public final String getStringParameter(String no) throws Exception {
        return this.getStringParameter(null, no);
    }

    public final String getStringParameter(Warehouse warehouse, String no) throws Exception {
        return this.parameterService.getValue(warehouse, no);
    }

    public final int getIntegerParameter(String no) throws Exception {
        return this.getIntegerParameter(null, no);
    }

    public final int getIntegerParameter(Warehouse warehouse, String no) throws Exception {
        return this.getDecimalParameter(warehouse, no).intValue();
    }

    public final BigDecimal getDecimalParameter(String no) throws Exception {
        return this.getDecimalParameter(null, no);
    }

    public final BigDecimal getDecimalParameter(Warehouse warehouse, String no) throws Exception {
        Pattern pattern = Pattern.compile("[0-9]*");
        String value = this.parameterService.getValue(warehouse, no);
        return pattern.matcher(value).matches() ? new BigDecimal(value) : new BigDecimal(-1);
    }

    public final Date getDateParameter(String no) throws Exception {
        return this.getDateParameter(null, no);
    }

    public final Date getDateParameter(Warehouse warehouse, String no) throws Exception {
        return Date.valueOf(this.getStringParameter(warehouse, no));
    }

    public final boolean isEnable(String no) throws Exception {
        return this.isEnable(null, no);
    }

    public final boolean isEnable(Warehouse warehouse, String no) throws Exception {
        return Boolean.parseBoolean(this.getStringParameter(warehouse, no));
    }
}