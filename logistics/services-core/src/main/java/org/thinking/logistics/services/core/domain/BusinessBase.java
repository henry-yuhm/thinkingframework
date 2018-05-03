package org.thinking.logistics.services.core.domain;

import lombok.Data;
import org.thinking.logistics.services.core.domain.employee.Employee;
import org.thinking.logistics.services.core.service.ParameterService;

import javax.annotation.Resource;
import javax.persistence.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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

    public final String getStringParameter(String no) {
        return this.getStringParameter(null, no);
    }

    public final String getStringParameter(Warehouse warehouse, String no) {
        return this.parameterService.getValue(warehouse, no);
    }

    public final int getIntegerParameter(String no) {
        return this.getIntegerParameter(null, no);
    }

    public final int getIntegerParameter(Warehouse warehouse, String no) {
        return this.getDecimalParameter(warehouse, no).intValue();
    }

    public final BigDecimal getDecimalParameter(String no) {
        return this.getDecimalParameter(null, no);
    }

    public final BigDecimal getDecimalParameter(Warehouse warehouse, String no) {
        Pattern pattern = Pattern.compile("[0-9]*");
        String value = this.parameterService.getValue(warehouse, no);
        return pattern.matcher(value).matches() ? new BigDecimal(value) : new BigDecimal(-1);
    }

    public final Date getDateParameter(String no) {
        return this.getDateParameter(null, no);
    }

    public final Date getDateParameter(Warehouse warehouse, String no) {
        return Date.valueOf(this.getStringParameter(warehouse, no));
    }

    public final boolean isEnable(String no) {
        return this.isEnable(null, no);
    }

    public final boolean isEnable(Warehouse warehouse, String no) {
        return Boolean.parseBoolean(this.getStringParameter(warehouse, no));
    }

    public final void verify(List<? extends EntityBase> entities, Class<? extends EntityBase> clazz) throws Exception {
        List<Field> fields = Arrays.asList(clazz.getFields());
        Arrays.asList(clazz.getDeclaredFields()).forEach(field -> {
            if (fields.stream().noneMatch(f -> f.getName().equalsIgnoreCase(field.getName()))) {
                fields.add(field);
            }
        });

        for (Field field : fields) {
            if (field.isAnnotationPresent(Id.class)) {
                continue;
            }

            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getDeclaredAnnotation(Column.class);

                for (EntityBase entity : entities) {
                    if (!column.nullable()) {
                        if (!Optional.ofNullable(entity.getClass().getField(field.getName()).get(field.getClass().newInstance())).isPresent()) {
                            entity.getClass().getField(field.getName()).set(field.getClass().newInstance(), "【" + field.getName() + "】不允许为空");
                        }
                    }

                    if (field.getType() == int.class || field.getType() == BigDecimal.class) {

                    }
                }
            }

            if (field.isAnnotationPresent(OneToOne.class) || field.isAnnotationPresent(ManyToOne.class)) {

            }

            if (field.isAnnotationPresent(OneToMany.class) || field.isAnnotationPresent(ManyToMany.class)) {

            }
        }
    }
}