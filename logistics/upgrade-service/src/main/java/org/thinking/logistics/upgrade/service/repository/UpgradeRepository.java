package org.thinking.logistics.upgrade.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.upgrade.service.entity.UpgradeConfig;

import java.util.List;

/**
 * Created by Henry on 2017/3/30.
 */
@Repository
public interface UpgradeRepository extends JpaRepository<UpgradeConfig, String>, QuerydslPredicateExecutor<UpgradeConfig> {
    List<UpgradeConfig> findAllByIsAvailableAndIsUpgradedOrderByUpgradeOrder(String isAvailable, String isUpgraded);
}