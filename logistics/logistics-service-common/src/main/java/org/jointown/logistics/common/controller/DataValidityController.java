package org.jointown.logistics.common.controller;

import org.jointown.logistics.common.service.DataValidityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/dataValidity")
public class DataValidityController {
    @Autowired
    private DataValidityService dataValidityService;

    @GetMapping("/getErrorsForEmpty")
    public String getErrorsForEmpty(@RequestParam("name") String name,
                                    @RequestParam("data") String data) {
        return this.dataValidityService.getErrorsForEmpty(name, data);
    }

    @GetMapping("/getErrorsForNumeric")
    public String getErrorsForNumeric(@RequestParam("name") String name,
                                      @RequestParam("data") String data) {
        return this.dataValidityService.getErrorsForNumeric(name, data);
    }

    @GetMapping("/getErrorsForLength")
    public String getErrorsForLength(@RequestParam("name") String name,
                                     @RequestParam("data") String data,
                                     @RequestParam("length") int length) {
        return this.dataValidityService.getErrorsForLength(name, data, length);
    }

    @GetMapping("/getErrorsForDateFormat")
    public String getErrorsForDateFormat(@RequestParam("name") String name,
                                         @RequestParam("data") String data,
                                         @RequestParam("dateFormat") String dateFormat) {
        return this.dataValidityService.getErrorsForDateFormat(name, data, dateFormat);
    }

    @GetMapping("/getErrorsForZero")
    public String getErrorsForZero(@RequestParam("name") String name,
                                   @RequestParam("data") BigDecimal data) {
        return this.dataValidityService.getErrorsForZero(name, data);
    }

    @GetMapping("/getErrorsForData")
    public String getErrorsForData(@RequestParam("tableName") String tableName,
                                   @RequestParam("data") String data) {
        return this.dataValidityService.getErrorsForData(tableName, data);
    }
}
