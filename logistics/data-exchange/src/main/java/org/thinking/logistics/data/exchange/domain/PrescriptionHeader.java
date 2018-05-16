package org.thinking.logistics.data.exchange.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.thinking.logistics.data.exchange.domain.support.Sex;
import org.thinking.logistics.data.exchange.domain.support.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
public class PrescriptionHeader implements EntityBase {
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
    @JSONField(name = "HospitalCode")
    private String hospitalCode;//医院编码

    @Column(nullable = false, length = 40)
    @JSONField(name = "RecipeNO")
    private String prescriptionNo;//处方号

    @Column(nullable = false, length = 20)
    @JSONField(name = "InvoiceNO")
    private String invoiceNo;//发票号

    @Column(nullable = false, length = 20)
    @JSONField(name = "DistributionSiteCode")
    private String distributionSiteCode;//配送点编码

    @Column(nullable = false)
    @JSONField(name = "RecipeTime", format = "YYYY-MM-DD HH:MM:SS")
    private Date prescriptionTime;//处方时间

    @Column(nullable = false, length = 20)
    @JSONField(name = "PatientNO")
    private String patientNo;//患者编号

    @Column(nullable = false, length = 64)
    @JSONField(name = "Patient")
    private String patient;//患者姓名

    @Column(nullable = false)
    @JSONField(name = "Sex")
    private Sex sex;//性别

    @Column(nullable = false, length = 2)
    @JSONField(name = "Age")
    private int age;//年龄

    @Column(length = 40)
    @JSONField(name = "Dept")
    private String dept;//患者科室

    @Column(length = 200)
    @JSONField(name = "Diagnosis")
    private String diagnosis;//诊断

    @Column(length = 30)
    @JSONField(name = "ContactNumber")
    private String contactNumber;//联系电话

    @Column(length = 30)
    @JSONField(name = "MobileNumber")
    private String mobileNumber;//手机号码

    @Column(length = 6)
    @JSONField(name = "Zip")
    private String zip;//邮编

    @Column(length = 200)
    @JSONField(name = "Addr")
    private String address;//联系地址

    @JSONField(name = "Type")
    private Type type;//代煎代配类型

    @Column(length = 512)
    @JSONField(name = "Desc")
    private String description;//代煎代配说明

    @Column(length = 512)
    @JSONField(name = "Usage")
    private String signature;//用法说明

    @Column(length = 512)
    @JSONField(name = "Requirement")
    private String requirement;//煎药要求

    @JSONField(name = "HandMade")
    private boolean handMade;//是否手工方

    @JSONField(name = "Dense")
    private boolean dense;//是否浓煎

    @JSONField(name = "FromWard")
    private boolean fromWard;//是否病房带回

    @Column(length = 64)
    @JSONField(name = "Doctor")
    private String doctor;//医生

    @Column(precision = 16, scale = 4)
    @JSONField(name = "RecipeNumber")
    private BigDecimal prescriptionQuantity;//处方贴数

    @Column(length = 3)
    @JSONField(name = "Total")
    private int total;//总记录数

    @Column(length = 256)
    @JSONField(name = "Memo")
    private String memo;//备注

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(joinColumns = @JoinColumn(name = "header_id"), inverseJoinColumns = @JoinColumn(name = "detail_id"))
    private Set<PrescriptionDetail> details = new LinkedHashSet<>();//单据明细
}