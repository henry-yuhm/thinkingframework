package org.jointown.logistics.common.service;

import org.jointown.logistics.common.configurer.DataInterfaceConfigurer;
import org.jointown.logistics.common.domain.DataInterfaceParameter;
import org.jointown.logistics.common.domain.DataInterfaceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Henry on 2017/4/1.
 */
@Service
public class DataInterfaceService {
    @Autowired
    private DataInterfaceConfigurer dataInterfaceConfigurer;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DataInterfaceParameter dataInterfaceParameter;

    @Autowired
    private StreamComputingService streamComputingService;

    public String call(String serviceName,
                       String requestType,
                       String jsonParas) {
        this.dataInterfaceParameter.setServiceName(serviceName);
        this.dataInterfaceParameter.setRequestType(requestType);
        this.dataInterfaceParameter.setJsonParas(jsonParas);

        return this.streamComputingService.getStreamComputingResult(this.restTemplate.postForObject(this.dataInterfaceConfigurer.getUrl(), this.dataInterfaceParameter, DataInterfaceResult.class));
    }

    public String call1() {
        String msg = "{\"TEST\":\"testteset\"}";
        String inparam = "[{\"" + "Parallel_Computing" + "\":" + msg + "}]";

        this.dataInterfaceParameter.setServiceName("ServoKfkInf");
        this.dataInterfaceParameter.setRequestType("POST");
        this.dataInterfaceParameter.setJsonParas(inparam);

        return this.streamComputingService.getStreamComputingResult(this.restTemplate.postForObject(this.dataInterfaceConfigurer.getUrl(), this.dataInterfaceParameter, DataInterfaceResult.class));
    }
}