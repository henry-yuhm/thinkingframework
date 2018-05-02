package org.thinking.logistics.services.core.repository.bill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.thinking.logistics.services.core.domain.Owner;
import org.thinking.logistics.services.core.domain.bill.Header;

@NoRepositoryBean
public interface HeaderRepository<H extends Header> extends JpaRepository<H, Long> {
    H findByOwnerAndNo(Owner owner, String no);
}