package org.jointown.logistics.common.controller;

import com.querydsl.core.types.Predicate;
import org.jointown.logistics.common.entity.Lot;
import org.jointown.logistics.common.service.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lot")
public class LotController {
    @Autowired
    private LotService lotService;

    @GetMapping("/findOne")
    public Lot findOne(@QuerydslPredicate(root = Lot.class) Predicate predicate) {
        return this.lotService.findOne(predicate);
    }

    @GetMapping("/findAll")
    public List<Lot> findAll(@QuerydslPredicate(root = Lot.class) Predicate predicate) {
        return this.lotService.findAll(predicate);
    }

    @PostMapping("/save")
    public String save(@RequestBody String data) {
        return this.lotService.save(data);
    }
}
