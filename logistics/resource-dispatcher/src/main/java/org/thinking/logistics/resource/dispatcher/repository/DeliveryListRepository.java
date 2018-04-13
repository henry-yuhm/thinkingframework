package org.thinking.logistics.resource.dispatcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.resource.dispatcher.entity.DeliveryList;

/**
 * Created by Henry on 2017/6/14.
 */
@Repository
public interface DeliveryListRepository extends JpaRepository<DeliveryList, DeliveryList.DeliveryListPk>, QuerydslPredicateExecutor<DeliveryList> {
}