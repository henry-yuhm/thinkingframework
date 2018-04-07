package org.thinking.logistics.stock.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.thinking.logistics.stock.configurer.FeignConfigurer;

import javax.validation.constraints.NotNull;

@FeignClient(name = "logistics-service-core",
        qualifier = "streamComputingClient",
        configuration = FeignConfigurer.class,
        fallbackFactory = StreamComputingClientFallbackFactory.class,
        path = "/streamComputing")
public interface StreamComputingClient {
    @RequestMapping(value = "/getStreamComputingResult",
            method = RequestMethod.GET)
    @NotNull
    String getStreamComputingResult(@RequestParam(name = "code") @NotNull boolean code,
                                    @RequestParam(name = "message") @NotNull String message,
                                    @RequestParam(name = "returnPara") @NotNull String returnPara);
}