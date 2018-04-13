package org.thinking.logistics.services.core.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
public class Parameter {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true, nullable = false, length = 50)
    private String number;//编号

    @Column(nullable = false)
    private String name;//名称

    @Column(nullable = false)
    private String value;//值

    @Column(nullable = false)
    private String sign;//标识

    @Embedded
    @OneToMany
    private Set<Range> ranges = new LinkedHashSet<>();//值域

    private String remarks;//备注

    @Embeddable
    @Data
    public static class Range {
        @Column(nullable = false)
        private String warehouse;//仓库

        @Column(nullable = false)
        private String value;//值
    }
}