package org.thinking.logistics.upgrade.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Henry on 2017/4/1.
 */
@FeignClient(name = "logistics-service-core", path = "/core/dataInterface")
public interface DataInterfaceClient {
    @RequestMapping(value = "/call", method = RequestMethod.GET)
    String call(@RequestParam("serviceName") String serviceName, @RequestParam("serviceParameters") String serviceParameters);
}