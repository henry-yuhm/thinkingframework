package org.jointown.logistics.common.entity;

import javax.persistence.*;

@Entity
public class Totebox extends Container {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_tbx_tb"))
    private TaskBill taskBill;//任务单
}