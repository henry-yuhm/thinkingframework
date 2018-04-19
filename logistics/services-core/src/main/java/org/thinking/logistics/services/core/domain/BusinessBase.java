package org.thinking.logistics.services.core.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.thinking.logistics.services.core.entity.Employee;
import org.thinking.logistics.services.core.entity.EntityBase;
import org.thinking.logistics.services.core.entity.Warehouse;
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
@NoArgsConstructor
public abstract class BusinessBase {
    protected Employee operator;//操作员

    @Resource
    private ParameterService parameterService;

    public BusinessBase(Employee operator) {
        this.operator = operator;
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