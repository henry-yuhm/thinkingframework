package org.jointown.logistics.core.entity.container;

import org.jointown.logistics.core.entity.task.RemainderTask;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Totebox extends Container {
    @ManyToOne(fetch = FetchType.LAZY)
    private RemainderTask task;//任务

    public Totebox() {
    }

    public RemainderTask getTask() {
        return task;
    }

    public void setTask(RemainderTask task) {
        this.task = task;
    }
}