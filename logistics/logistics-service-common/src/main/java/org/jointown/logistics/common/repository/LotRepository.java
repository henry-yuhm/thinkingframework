package org.jointown.logistics.common.repository;

import org.jointown.logistics.common.entity.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LotRepository extends JpaRepository<Lot, String>, JpaSpecificationExecutor<Lot>, QueryDslPredicateExecutor<Lot> {
}
