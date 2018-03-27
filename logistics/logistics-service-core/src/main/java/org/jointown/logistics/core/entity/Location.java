package org.jointown.logistics.core.entity;

import org.jointown.logistics.core.entity.support.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Location {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Warehouse warehouse;//仓库

    @Column(nullable = false)
    private String no;//编号

    @Column(nullable = false)
    private String floor;//楼层

    @OneToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Area area;//区域

    private String group;//分组

    private String roadway;//巷道

    private String row;//排

    private String layer;//层

    private String column;//列

    private String shortno;//短编号

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
    private boolean available;//是否可用

    @Column(nullable = false)
    private boolean locking;//是否锁定

    @Column(nullable = false)
    private boolean automatic;//是否自动化

    @Column(nullable = false)
    private LocationType type;//货位类型

    @Column(nullable = false)
    private RackMode rackMode;//货架模式

    @Column(nullable = false)
    private StorageType storageType;//存放类型

    @Column(nullable = false)
    private StorageStatus storageStatus;//存放状态

    @Column(nullable = false)
    private PileupType pileupType;//码放类型

    @OneToOne(fetch = FetchType.LAZY)
    private Transferline transferline;//输送线

    public Location() {
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getRoadway() {
        return roadway;
    }

    public void setRoadway(String roadway) {
        this.roadway = roadway;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
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