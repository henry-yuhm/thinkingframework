package org.thinking.logistics.resource.service;

import com.alibaba.fastjson.JSONObject;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thinking.logistics.resource.entity.DeliveryList;
import org.thinking.logistics.resource.repository.DeliveryListRepository;

import java.util.List;

/**
 * Created by Henry on 2017/6/14.
 */
@Service
public class DeliveryListService {
    private DeliveryListRepository deliveryListRepository;

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

//    public List<DeliveryListEntity> getDeliveryLists(String corporationNo, String billHeaderId) {
//        QDeliveryListEntity qDeliveryListEntity = QDeliveryListEntity.deliveryListEntity;
//
//        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
//        List<DeliveryListEntity> deliveryListEntities = jpaQueryFactory.selectFrom(qDeliveryListEntity)
//                .where(qDeliveryListEntity.corporationNo.eq(corporationNo)
//                        .and(qDeliveryListEntity.billHeaderId.eq(billHeaderId)))
//                .fetch()
//                .stream()
//                .collect(Collectors.toList());
//
//        return deliveryListEntities;
//    }

    public String getFileName(Predicate predicate) {
        return this.findOne(predicate).getFileName();
    }
}