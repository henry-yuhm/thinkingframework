package org.jointown.logistics.core.repository;

import org.jointown.logistics.core.entity.bill.Header;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface HeaderRepository<T extends Header> extends JpaRepository<T, Long> {
}