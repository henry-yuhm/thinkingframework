package org.thinking.logistics.operation.audit.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;

@Entity
@Data
public class AuditData {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, updatable = false)
    @NotBlank(message = "用户名不能为空")
    private String username;

    @Column(nullable = false, updatable = false)
    @NotBlank(message = "应用名称不能为空")
    private String applicationName;

    @Column(nullable = false, updatable = false)
    @NotBlank(message = "模块名称不能为空")
    private String moduleName;

    @ManyToOne
    @NotNull(message = "审计类型不能为空")
    private AuditType type;

    private HashMap<String, String> info;

    @Column(nullable = false, updatable = false)
    @NotNull(message = "审计时间不能为空")
    private Date auditTime;
}