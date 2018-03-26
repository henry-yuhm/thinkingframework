package org.jointown.logistics.common.entity;

import org.jointown.logistics.common.entity.support.TWFSign;
import org.jointown.logistics.common.entity.support.TransferlineSigns;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Warehouse {
    @Id
    @TableGenerator(name = "WarehouseId", table = "WarehouseId", initialValue = 1000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "WarehouseId")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_wh_own"))
    private Owner owner;//业主

    private String name;//名称

    private boolean electronicLabel;//是否有电子标签

    private boolean pallet;//是否使用托盘

    private boolean sorter;//是否有分拣机

    private boolean tablet;//是否使用平板电脑

    private Set<TransferlineSigns> transferlineSigns;//输送线标识

    private Set<TWFSign> twfSigns;//立体库标识

    public Warehouse() {
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isElectronicLabel() {
        return electronicLabel;
    }

    public void setElectronicLabel(boolean electronicLabel) {
        this.electronicLabel = electronicLabel;
    }

    public boolean isPallet() {
        return pallet;
    }

    public void setPallet(boolean pallet) {
        this.pallet = pallet;
    }

    public boolean isSorter() {
        return sorter;
    }

    public void setSorter(boolean sorter) {
        this.sorter = sorter;
    }

    public boolean isTablet() {
        return tablet;
    }

    public void setTablet(boolean tablet) {
        this.tablet = tablet;
    }

    public Set<TransferlineSigns> getTransferlineSigns() {
        return transferlineSigns;
    }

    public void setTransferlineSigns(Set<TransferlineSigns> transferlineSigns) {
        this.transferlineSigns = transferlineSigns;
    }

    public Set<TWFSign> getTwfSigns() {
        return twfSigns;
    }

    public void setTwfSigns(Set<TWFSign> twfSigns) {
        this.twfSigns = twfSigns;
    }
}