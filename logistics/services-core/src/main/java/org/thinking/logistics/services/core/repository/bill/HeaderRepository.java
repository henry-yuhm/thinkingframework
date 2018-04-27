package org.thinking.logistics.services.core.repository.bill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.entity.Owner;
import org.thinking.logistics.services.core.entity.bill.Header;

@Repository
public interface HeaderRepository<H extends Header> extends JpaRepository<H, Long> {
    H findByOwnerAndNo(Owner owner, String no);
}