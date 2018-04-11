package org.thinking.logistics.core.entity.container;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.core.entity.task.RemainderTask;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Totebox extends Container {
    @ManyToOne(fetch = FetchType.LAZY)
    private RemainderTask task;//任务
}