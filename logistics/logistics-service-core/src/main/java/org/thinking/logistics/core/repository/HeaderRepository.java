package org.thinking.logistics.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.core.entity.bill.Header;

@Repository
public interface HeaderRepository<S extends Header> extends JpaRepository<S, Long> {
}