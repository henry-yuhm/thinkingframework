package org.jointown.logistics.resource.service;

import com.alibaba.fastjson.JSONObject;
import com.querydsl.core.types.Predicate;
import org.jointown.logistics.resource.entity.DeliveryListEntity;
import org.jointown.logistics.resource.repository.DeliveryListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Henry on 2017/6/14.
 */
@Service
public class DeliveryListService {
    @Autowired
    private DeliveryListRepository deliveryListRepository;

    public DeliveryListEntity findOne(Predicate predicate) {
        return this.deliveryListRepository.findOne(predicate);
    }

    public List<DeliveryListEntity> findAll(Predicate predicate) {
        return (List<DeliveryListEntity>) this.deliveryListRepository.findAll(predicate);
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

    @Transactional(rollbackFor = Exception.class)
    public void save(String data) {
        this.deliveryListRepository.saveAndFlush(JSONObject.parseObject(data, DeliveryListEntity.class));
    }

    public String getFileName(Predicate predicate) {
        return this.findOne(predicate).getFileName();
    }
}