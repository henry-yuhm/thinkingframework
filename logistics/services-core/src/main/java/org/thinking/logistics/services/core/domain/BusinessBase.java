package org.thinking.logistics.services.core.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.Data;
import org.thinking.logistics.services.core.entity.EntityBase;
import org.thinking.logistics.services.core.entity.Warehouse;
import org.thinking.logistics.services.core.entity.employee.Employee;
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
    protected JPAQueryFactory queryFactory;

    protected Employee operator;//操作员

    @Resource
    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private ParameterService parameterService;

    public BusinessBase() {
        this(null);
    }

    public BusinessBase(Employee operator) {
        this.queryFactory = new JPAQueryFactory(this.entityManager);
        this.operator = operator;
    }

    protected final String getStringParameter(String no) {
        return this.getStringParameter(null, no);
    }

    protected final String getStringParameter(Warehouse warehouse, String no) {
        return this.parameterService.getValue(warehouse, no);
    }

    protected final int getIntegerParameter(String no) {
        return this.getIntegerParameter(null, no);
    }

    protected final int getIntegerParameter(Warehouse warehouse, String no) {
        return this.getDecimalParameter(warehouse, no).intValue();
    }

    protected final BigDecimal getDecimalParameter(String no) {
        return this.getDecimalParameter(null, no);
    }

    protected final BigDecimal getDecimalParameter(Warehouse warehouse, String no) {
        Pattern pattern = Pattern.compile("[0-9]*");
        String value = this.parameterService.getValue(warehouse, no);
        return pattern.matcher(value).matches() ? new BigDecimal(value) : new BigDecimal(-1);
    }

    protected final Date getDateParameter(String no) {
        return this.getDateParameter(null, no);
    }

    protected final Date getDateParameter(Warehouse warehouse, String no) {
        return Date.valueOf(this.getStringParameter(warehouse, no));
    }

    protected final boolean isEnable(String no) {
        return this.isEnable(null, no);
    }

    protected final boolean isEnable(Warehouse warehouse, String no) {
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