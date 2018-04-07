package org.thinking.logistics.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.stock.entity.Invoice;

/**
 * Created by Henry on 2017/3/29.
 */
@Repository
public interface InvoicingRepository extends JpaRepository<Invoice, Long>, QueryDslPredicateExecutor<Invoice> {
}