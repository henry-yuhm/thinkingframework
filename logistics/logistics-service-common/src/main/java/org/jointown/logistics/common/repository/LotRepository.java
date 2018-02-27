package org.jointown.logistics.common.repository;

import org.jointown.logistics.common.entity.LotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LotRepository extends JpaRepository<LotEntity, String>, JpaSpecificationExecutor<LotEntity>, QueryDslPredicateExecutor<LotEntity> {
}
