package org.jointown.logistics.workflow.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.io.Serializable;

@Entity
public class Monitor implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    private String machineId;

    @Lob
    private String stateContext;

    public Monitor() {
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public String getStateContext() {
        return stateContext;
    }

    public void setStateContext(String stateContext) {
        this.stateContext = stateContext;
    }
}