package org.thinking.logistics.data.exchange.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.thinking.logistics.data.exchange.domain.support.DownloadState;

import java.util.Date;

@Data
public class Request {
    @JSONField(name = "BusinessType")
    private String businessType;

    @JSONField(name = "HospitalCode")
    private String hospitalCode;

    @JSONField(name = "IP")
    private String ip;

    @JSONField(name = "MAC")
    private String mac;

    @JSONField(name = "HostName")
    private String hostName;

    @JSONField(name = "Data")
    private Criteria criteria;

    @Data
    public static class Criteria {
        @JSONField(name = "Kssj")
        private Date startTime;

        @JSONField(name = "Jssj")
        private Date endTime;

        @JSONField(name = "Count")
        private int count = 1;

        @JSONField(name = "Page")
        private int page;

        @JSONField(name = "SupplierCode")
        private String supplierCode;

        @JSONField(name = "DownloadState")
        private DownloadState downloadState;

        @JSONField(name = "Managed")
        private boolean managed = true;

        @JSONField(name = "ID")
        private String platformId;//平台处方主键
    }
}