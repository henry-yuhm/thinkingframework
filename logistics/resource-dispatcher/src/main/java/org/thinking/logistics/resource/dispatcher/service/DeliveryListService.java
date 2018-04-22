package org.thinking.logistics.resource.dispatcher.service;

import com.alibaba.fastjson.JSONObject;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thinking.logistics.resource.dispatcher.entity.DeliveryList;
import org.thinking.logistics.resource.dispatcher.repository.DeliveryListRepository;

import java.util.List;

/**
 * Created by Henry on 2017/6/14.
 */
@Service
public class DeliveryListService {
    private DeliveryListRepository deliveryListRepository;

    @Autowired
    public DeliveryListService(DeliveryListRepository deliveryListRepository) {
        this.deliveryListRepository = deliveryListRepository;
    }

    public DeliveryList findOne(Predicate predicate) {
        return this.deliveryListRepository.findOne(predicate).get();
    }

    public List<DeliveryList> findAll(Predicate predicate) {
        return (List<DeliveryList>) this.deliveryListRepository.findAll(predicate);
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(String data) {
        this.deliveryListRepository.saveAndFlush(JSONObject.parseObject(data, DeliveryList.class));
    }

    public String acquireFileName(Predicate predicate) {
        return this.findOne(predicate).getFileName();
    }
}