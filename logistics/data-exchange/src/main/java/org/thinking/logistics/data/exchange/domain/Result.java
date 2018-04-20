package org.thinking.logistics.data.exchange.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.thinking.logistics.data.exchange.domain.support.ResponseCode;

@Data
public class Result {
    @JSONField(name = "OrderNo")
    private String orderNo;

    @JSONField(name = "Result")
    private boolean result;

    @JSONField(name = "Code")
    private ResponseCode code;

    @JSONField(name = "Message")
    private String message;
}