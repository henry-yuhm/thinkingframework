package org.jointown.logistics.stock.client;

import org.jointown.logistics.stock.configurer.FeignConfigurer;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;

@FeignClient(name = "logistics-service-core",
        configuration = FeignConfigurer.class,
        fallbackFactory = DataValidityClientFallbackFactory.class,
        path = "/dataValidity")
public interface DataValidityClient {
    @RequestMapping(value = "/getErrorsForData",
            method = RequestMethod.GET)
    @NotNull
    String getErrorsForData(@RequestParam("tableName") @NotNull String tableName,
                            @RequestParam("data") @NotNull String data);
}