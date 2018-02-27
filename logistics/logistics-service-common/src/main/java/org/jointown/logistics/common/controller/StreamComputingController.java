package org.jointown.logistics.common.controller;

import org.jointown.logistics.common.service.StreamComputingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/streamComputing")
public class StreamComputingController {
    @Autowired
    private StreamComputingService streamComputingService;

    @GetMapping("/getStreamComputingResult")
    public String getStreamComputingResult(@RequestParam(name = "code") boolean code,
                                           @RequestParam(name = "message") String message,
                                           @RequestParam(name = "returnPara") String returnPara) {
        return this.streamComputingService.getStreamComputingResult(code, message, returnPara);
    }
}