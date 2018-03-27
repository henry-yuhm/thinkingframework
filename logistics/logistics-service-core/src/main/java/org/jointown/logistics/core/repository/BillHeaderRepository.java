package org.jointown.logistics.core.repository;

import org.jointown.logistics.core.entity.bill.BillHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillHeaderRepository extends JpaRepository<BillHeader, Long> {
}