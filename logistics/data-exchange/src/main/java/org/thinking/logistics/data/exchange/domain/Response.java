package org.thinking.logistics.data.exchange.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.thinking.logistics.data.exchange.domain.support.ResponseCode;

@Data
public class Response {
    @JSONField(name = "Code")
    private ResponseCode code;

    @JSONField(name = "Message")
    private String message;

    @JSONField(name = "Completed")
    private boolean complete = false;

    @JSONField(name = "Data")
    private String data;
}