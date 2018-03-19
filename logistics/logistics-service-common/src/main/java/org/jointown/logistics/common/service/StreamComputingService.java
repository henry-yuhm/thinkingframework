package org.jointown.logistics.common.service;

import com.alibaba.fastjson.JSONObject;
import org.jointown.logistics.common.domain.DataInterfaceResult;
import org.jointown.logistics.common.domain.StreamComputingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StreamComputingService {
    @Autowired
    private StreamComputingResult streamComputingResult;

    public String getStreamComputingResult(DataInterfaceResult dataInterfaceResult) {
        this.streamComputingResult.setCode(dataInterfaceResult.isFlag());

        if (dataInterfaceResult.isFlag()) {
            this.streamComputingResult.setMessage("");
            this.streamComputingResult.setReturnPara(JSONObject.toJSONString(dataInterfaceResult.getMessageInformation()));
        } else {
            this.streamComputingResult.setMessage(JSONObject.toJSONString(dataInterfaceResult.getErrorInformation()));
            this.streamComputingResult.setReturnPara("");
        }

        return JSONObject.toJSONString(this.streamComputingResult);
    }

    public String getStreamComputingResult(boolean code,
                                           String message,
                                           String returnPara) {
        this.streamComputingResult.setCode(code);
        this.streamComputingResult.setMessage(message);
        this.streamComputingResult.setReturnPara(returnPara);

        return JSONObject.toJSONString(this.streamComputingResult);
    }
}