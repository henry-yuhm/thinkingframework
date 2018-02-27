package org.jointown.logistics.upgrade.repository;

import org.jointown.logistics.upgrade.entity.UpgradeConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Henry on 2017/3/30.
 */
@Repository
public interface UpgradeConfigRepository extends JpaRepository<UpgradeConfigEntity, String>, JpaSpecificationExecutor<UpgradeConfigEntity>, QueryDslPredicateExecutor<UpgradeConfigEntity> {
    List<UpgradeConfigEntity> findAllByIsAvailableAndIsUpgradedOrderByUpgradeOrder(String isAvailable,
                                                                                   String isUpgraded);
}