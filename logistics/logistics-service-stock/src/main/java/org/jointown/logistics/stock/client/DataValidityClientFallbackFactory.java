package org.jointown.logistics.stock.client;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class DataValidityClientFallbackFactory implements FallbackFactory<DataValidityClient> {
    @Override
    public DataValidityClient create(Throwable cause) {
        return (tableName, data) -> cause.getMessage();
    }
}