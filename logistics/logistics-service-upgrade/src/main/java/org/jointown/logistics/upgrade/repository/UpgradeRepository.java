package org.jointown.logistics.upgrade.repository;

import org.jointown.logistics.upgrade.entity.UpgradeConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Henry on 2017/3/30.
 */
@Repository
public interface UpgradeRepository extends JpaRepository<UpgradeConfig, String>, JpaSpecificationExecutor<UpgradeConfig>, QueryDslPredicateExecutor<UpgradeConfig> {
    List<UpgradeConfig> findAllByIsAvailableAndIsUpgradedOrderByUpgradeOrder(String isAvailable, String isUpgraded);
}