package org.thinking.logistics.stock.client;

import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInterfaceClientFallbackFactory implements FallbackFactory<DataInterfaceClient> {
    @Autowired
    private StreamComputingClient streamComputingClient;

    @Override
    public DataInterfaceClient create(Throwable cause) {
        return (serviceName, requestType, serviceParameters) -> this.streamComputingClient.getStreamComputingResult(false, cause.getMessage(), "");
    }
}