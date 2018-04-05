package org.jointown.logistics.core.repository;

import org.jointown.logistics.core.entity.bill.Header;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeaderRepository<S extends Header> extends JpaRepository<S, Long> {
}