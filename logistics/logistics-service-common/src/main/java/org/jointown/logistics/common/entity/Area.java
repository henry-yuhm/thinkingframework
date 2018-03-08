package org.jointown.logistics.common.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class Area implements Serializable {
    @Id
    private long id;

    @OneToOne
    private Warehouse warehouse;

    private String code;

    private String description;

    private String storeCategory;

    private String storeNo;

    private String packageSign;

    private String operationGroup;

    private String pickingDeviceSign;

    private String upShelfMode;

    private String isPrePicking;

    private String isUseSorter;

    private String innerRecheckSign;

    private int fullloadToteBoxNumber;

    public Area() {
    }
}