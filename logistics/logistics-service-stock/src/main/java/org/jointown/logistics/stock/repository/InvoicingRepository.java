package org.jointown.logistics.stock.repository;

import org.jointown.logistics.stock.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by Henry on 2017/3/29.
 */
@Repository
public interface InvoicingRepository extends JpaRepository<Invoice, Long>, JpaSpecificationExecutor<Invoice>, QueryDslPredicateExecutor<Invoice> {
}