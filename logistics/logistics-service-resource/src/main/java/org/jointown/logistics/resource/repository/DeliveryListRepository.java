package org.jointown.logistics.resource.repository;

import org.jointown.logistics.resource.entity.DeliveryList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by Henry on 2017/6/14.
 */
@Repository
public interface DeliveryListRepository extends JpaRepository<DeliveryList, DeliveryList.DeliveryListPk>, JpaSpecificationExecutor<DeliveryList>, QueryDslPredicateExecutor<DeliveryList> {
}