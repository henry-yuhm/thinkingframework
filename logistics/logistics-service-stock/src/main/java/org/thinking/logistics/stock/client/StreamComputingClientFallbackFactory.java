package org.thinking.logistics.stock.client;

import com.alibaba.fastjson.JSONObject;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StreamComputingClientFallbackFactory implements FallbackFactory<StreamComputingClient> {
    @Override
    public StreamComputingClient create(Throwable cause) {
        return (code, message, returnPara) -> {
            Map<String, Object> map = new HashMap<>();

            map.put("Code", false);
            map.put("Message", cause.getMessage());
            map.put("ReturnPara", "");

            return JSONObject.toJSONString(map);
        };
    }
}