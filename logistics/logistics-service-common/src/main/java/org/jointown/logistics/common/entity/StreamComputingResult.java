package org.jointown.logistics.common.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Henry on 2017/3/10.
 */
public class StreamComputingResult {
    @JSONField(name = "Code")
    private boolean code;

    @JSONField(ordinal = 1,
            name = "Message")
    private String message;

    @JSONField(ordinal = 2,
            name = "ReturnPara")
    private String returnPara;

    public StreamComputingResult() {
    }

    public boolean isCode() {
        return code;
    }

    public void setCode(boolean code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReturnPara() {
        return returnPara;
    }

    public void setReturnPara(String returnPara) {
        this.returnPara = returnPara;
    }
}