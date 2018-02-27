package org.jointown.logistics.common.controller;

import org.jointown.logistics.common.service.DataInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Henry on 2017/4/5.
 */
@RestController
@RequestMapping("/dataInterface")
public class DataInterfaceController {
    @Autowired
    private DataInterfaceService dataInterfaceService;

    @PostMapping("/call")
    public String call(@RequestParam(name = "serviceName") String serviceName,
                       @RequestParam("requestType") String requestType,
                       @RequestParam(name = "serviceParameters") String serviceParameters) {
        return this.dataInterfaceService.call(serviceName, requestType, serviceParameters);
    }

    @PostMapping("/call1")
    public String call1() {
        return this.dataInterfaceService.call1();
    }
}