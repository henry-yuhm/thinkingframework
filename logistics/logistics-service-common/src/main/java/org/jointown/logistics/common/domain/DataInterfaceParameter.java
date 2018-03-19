package org.jointown.logistics.common.domain;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Henry on 2017/4/5.
 */
public class DataInterfaceParameter {
    @JSONField(name = "serviceName")
    private String serviceName;

    @JSONField(ordinal = 1,
            name = "requestType")
    private String requestType;

    @JSONField(ordinal = 2,
            name = "jsonParas")
    private String jsonParas;

    public DataInterfaceParameter() {
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getJsonParas() {
        return jsonParas;
    }

    public void setJsonParas(String jsonParas) {
        this.jsonParas = jsonParas;
    }
}