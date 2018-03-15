package org.jointown.logistics.stock.controller;

import com.querydsl.core.types.Predicate;
import org.jointown.logistics.stock.entity.Stock;
import org.jointown.logistics.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Henry on 2017/3/30.
 */
@RestController
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private StockService stockService;

    @GetMapping("/findOne")
    public Stock findOne(@QuerydslPredicate(root = Stock.class) Predicate predicate) {
        return this.stockService.findOne(predicate);
    }

    @GetMapping("/findAll")
    public List<Stock> findAll(@QuerydslPredicate(root = Stock.class) Predicate predicate) {
        return this.stockService.findAll(predicate);
    }

//    @GetMapping("/findAllBy")
//    public List<Stock> findAllBy(@RequestParam("ownerNo") String ownerNo) {
//        return this.stockService.findAllBy(ownerNo);
//    }

    @GetMapping("/getStock")
    public String getStock(@RequestParam("goodsId") String goodsId,
                           @RequestParam("lotNo") String lotNo) {
        return this.stockService.getStock(goodsId, lotNo);
    }

    @PostMapping("/save")
    public String save(@RequestBody String data) {
        return this.stockService.save(data);
    }

    @PostMapping("/test")
    public Map<String, Object> test(@RequestBody Map<String, Object> parameters) {
        parameters.keySet().forEach(key -> System.out.println("调用到这个方法了!参数是:" + parameters.get(key).toString()));
        parameters.put("kkk", "789");
        return parameters;
    }
}