package org.thinking.logistics.data.exchange.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.thinking.logistics.data.exchange.domain.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class PrescriptionDetail implements EntityBase {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length = 20)
    @JSONField(name = "ID")
    private String platformId;//平台处方主键

    @Column(nullable = false, length = 40)
    @JSONField(name = "ID3")
    private String hospitalId;//医院处方主键

    @Column(nullable = false, length = 32)
    @JSONField(name = "UniCode")
    private String uniCode;//商品统一编码

    @Column(length = 256)
    @JSONField(name = "Name")
    private String name;//药品名称

    @Column(length = 256)
    @JSONField(name = "Manufacturer")
    private String manufacturer;//生产厂家

    @Column(nullable = false, length = 64)
    @JSONField(name = "Unit")
    private String unit;//采购单位

    @Column(nullable = false, precision = 18, scale = 4)
    @JSONField(name = "Quantity")
    private BigDecimal quantity = BigDecimal.ZERO;//数量

    @Column(nullable = false, precision = 18, scale = 4)
    @JSONField(name = "Price")
    private BigDecimal price = BigDecimal.ZERO;//单价

    @Column(nullable = false, precision = 18, scale = 4)
    @JSONField(name = "Amount")
    private BigDecimal amount = BigDecimal.ZERO;//金额

    @Column(length = 64)
    @JSONField(name = "EachUnit")
    private String pieceUnit;//单贴单位

    @Column(nullable = false, precision = 18, scale = 4)
    @JSONField(name = "EachQuantity")
    private BigDecimal pieceQuantity = BigDecimal.ZERO;//单贴数量

    @Column(nullable = false, precision = 18, scale = 4)
    @JSONField(name = "EachPrice")
    private BigDecimal piecePrice = BigDecimal.ZERO;//单贴单价

    @Column(nullable = false, precision = 18, scale = 4)
    @JSONField(name = "EachAmount")
    private BigDecimal pieceAmount = BigDecimal.ZERO;//单贴金额
}