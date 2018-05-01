package org.thinking.logistics.services.core.entity.parameter;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Parameter {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true, nullable = false, length = 50)
    private String no;//编号

    @Column(nullable = false)
    private String name;//名称

    @Column(nullable = false)
    private String value;//值

    @Column(nullable = false)
    private String sign;//标识

    @OneToMany
    @JoinTable(joinColumns = @JoinColumn(name = "parameter_id"), inverseJoinColumns = @JoinColumn(name = "range_id"))
    private Set<ParameterRange> ranges = new LinkedHashSet<>();//值域

    private String remarks;//备注
}