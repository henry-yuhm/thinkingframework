package org.thinking.logistics.core.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.thinking.logistics.core.entity.Employee;
import org.thinking.logistics.core.entity.Owner;
import org.thinking.logistics.core.entity.Warehouse;
import org.thinking.logistics.core.repository.EmployeeRepository;
import org.thinking.logistics.core.service.ParameterService;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.regex.Pattern;

public abstract class BusinessAdapter {
    protected Employee operator;//操作员

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ParameterService parameterService;

    protected final Employee getEmployee(Owner owner, String number) {
        return this.employeeRepository.findByOwnerAndNumber(owner, number);
    }

    protected final String getNullDetailMessage() {
        return "单据明细不存在";
    }

    protected final String getNullCustomerMessage() {
        return "客户资料不存在";
    }

    protected final String getNullGoodsMessage() {
        return "商品资料不存在";
    }

    protected Exception getException(String message, Object... objects) {
        StringBuilder translatedMessage = new StringBuilder();

        if (message == null || message.isEmpty()) {
            return new Exception("必须定义基本消息内容");
        }

        //无辅助信息时直接抛出消息
        if (objects == null || objects.length == 0) {
            return new Exception(message);
        }

        //循环构造辅助信息并添加至消息中
        translatedMessage.append(message);
        for (Object object : objects) {
            translatedMessage.append(object.toString());
        }
        return new Exception(translatedMessage.toString());
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

    public Employee getOperator() {
        return operator;
    }

    public void setOperator(Employee operator) {
        this.operator = operator;
    }
}