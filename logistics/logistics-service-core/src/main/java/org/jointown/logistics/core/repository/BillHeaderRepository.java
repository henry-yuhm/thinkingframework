package org.jointown.logistics.core.repository;

import org.jointown.logistics.core.entity.bill.BillHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BillHeaderRepository<T extends BillHeader> extends JpaRepository<T, Long> {
}