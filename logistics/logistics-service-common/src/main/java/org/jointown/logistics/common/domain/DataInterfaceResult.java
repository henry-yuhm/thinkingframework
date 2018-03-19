package org.jointown.logistics.common.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;
import java.util.Map;

public class DataInterfaceResult {
    @JSONField(name = "Flag")
    private boolean flag;

    @JSONField(ordinal = 1,
            name = "MsgInfo")
    private List<Map<String, Object>> messageInformation;

    @JSONField(ordinal = 2,
            name = "ErrInfo")
    private List<Map<String, Object>> errorInformation;

    public DataInterfaceResult() {
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<Map<String, Object>> getMessageInformation() {
        return messageInformation;
    }

    public void setMessageInformation(List<Map<String, Object>> messageInformation) {
        this.messageInformation = messageInformation;
    }

    public List<Map<String, Object>> getErrorInformation() {
        return errorInformation;
    }

    public void setErrorInformation(List<Map<String, Object>> errorInformation) {
        this.errorInformation = errorInformation;
    }
}
