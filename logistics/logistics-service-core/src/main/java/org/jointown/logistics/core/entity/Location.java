package org.jointown.logistics.core.entity;

import org.jointown.logistics.core.entity.support.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Location {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private String number;//编号

    @Column(nullable = false)
    private String floor;//楼层

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Area area;//区域

    private String region;//大区

    private String roadway;//巷道

    private String x = "";//排

    private String y = "";//列

    private String z = "";//层

    private String shortno = x + y + z;//短编号

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal length;//长

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal width;//宽

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal height;//高

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal volume;//体积

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal occupationVolume;//占用体积

    @Column(nullable = false)
    private PackageSign sign;//包装标识

    @Column(nullable = false)
    private boolean available = true;//是否可用

    @Column(nullable = false)
    private boolean locking = false;//是否锁定

    @Column(nullable = false)
    private boolean automatic;//是否自动化

    @Column(nullable = false)
    private LocationType type = LocationType.NORMAL;//货位类型

    @Column(nullable = false)
    private RackMode rackMode = RackMode.CLAPBOARD;//货架模式

    @Column(nullable = false)
    private StorageType storageType;//存放类型

    @Column(nullable = false)
    private StorageStatus storageStatus;//存放状态

    @Column(nullable = false)
    private PileupType pileupType;//码放类型

    @ManyToOne(fetch = FetchType.LAZY)
    private Transferline transferline;//输送线

    public Location() {
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRoadway() {
        return roadway;
    }

    public void setRoadway(String roadway) {
        this.roadway = roadway;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getZ() {
        return z;
    }

    public void setZ(String z) {
        this.z = z;
    }

    public String getShortno() {
        return shortno;
    }

    public void setShortno(String shortno) {
        this.shortno = shortno;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getOccupationVolume() {
        return occupationVolume;
    }

    public void setOccupationVolume(BigDecimal occupationVolume) {
        this.occupationVolume = occupationVolume;
    }

    public PackageSign getSign() {
        return sign;
    }

    public void setSign(PackageSign sign) {
        this.sign = sign;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isLocking() {
        return locking;
    }

    public void setLocking(boolean locking) {
        this.locking = locking;
    }

    public boolean isAutomatic() {
        return automatic;
    }

    public void setAutomatic(boolean automatic) {
        this.automatic = automatic;
    }

    public LocationType getType() {
        return type;
    }

    public void setType(LocationType type) {
        this.type = type;
    }

    public RackMode getRackMode() {
        return rackMode;
    }

    public void setRackMode(RackMode rackMode) {
        this.rackMode = rackMode;
    }

    public StorageType getStorageType() {
        return storageType;
    }

    public void setStorageType(StorageType storageType) {
        this.storageType = storageType;
    }

    public StorageStatus getStorageStatus() {
        return storageStatus;
    }

    public void setStorageStatus(StorageStatus storageStatus) {
        this.storageStatus = storageStatus;
    }

    public PileupType getPileupType() {
        return pileupType;
    }

    public void setPileupType(PileupType pileupType) {
        this.pileupType = pileupType;
    }

    public Transferline getTransferline() {
        return transferline;
    }

    public void setTransferline(Transferline transferline) {
        this.transferline = transferline;
    }
}