package org.thinking.logistics.services.core.domain.container;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.services.core.domain.core.Task;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Totebox extends Container {
    @OneToOne(fetch = FetchType.LAZY)
    private Task task;//任务
}