package org.thinking.logistics.resource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.resource.entity.DeliveryList;

/**
 * Created by Henry on 2017/6/14.
 */
@Repository
public interface DeliveryListRepository extends JpaRepository<DeliveryList, DeliveryList.DeliveryListPk>, QueryDslPredicateExecutor<DeliveryList> {
}