package org.thinking.logistics.data.exchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thinking.logistics.data.exchange.service.PrescriptionService;

@RestController
@RequestMapping("/prescription")
public class PrescriptionController {
    private PrescriptionService service;

    @Autowired
    public PrescriptionController(PrescriptionService service) {
        this.service = service;
    }

    @PutMapping("/download")
    public void download() throws Exception {
        this.service.download();
    }

    @PutMapping("/test")
    public void test(@RequestBody String body) {
        this.service.test(body);
    }
}