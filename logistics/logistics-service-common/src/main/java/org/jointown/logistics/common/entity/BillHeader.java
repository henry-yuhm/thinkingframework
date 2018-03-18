package org.jointown.logistics.common.entity;

import javax.persistence.*;
import java.util.Set;

@MappedSuperclass
public abstract class BillHeader {
    @Id
    @GeneratedValue
    private long id;

    private String no;

    @OneToOne
    private Warehouse warehouse;

    @OneToOne
    private Owner owner;

    @OneToOne
    private Customer customer;

    private Stage stage;

    @ManyToMany
    private Set<? extends BillDetail> billDetails;

    public BillHeader() {
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Set<? extends BillDetail> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(Set<? extends BillDetail> billDetails) {
        this.billDetails = billDetails;
    }

    public enum Stage {
        ORDER_GENERATED,
        INIT_FINISHED,
        WAVE_ARRANGED,
        WAVE_RELEASED,
        STAGINGAREA_ALLOCATION_FINISHED,
        STOCKLESSNESS_SUSPENDED,
        STOCKLESSNESS_RELEASED,
        LOT_ALLOCATION_FINISHED,
        TASKS_BALING_FINISHED,
        SPLITING_FINISHED,
        OPERATION_AVAILABLE,
        ON_WORKING,
        INNER_RECHECK_FINISHED,
        ON_OUTER_RECHECKING,
        OUTER_RECHECK_FINISHED,
        OPERATION_FINISHED
    }
}