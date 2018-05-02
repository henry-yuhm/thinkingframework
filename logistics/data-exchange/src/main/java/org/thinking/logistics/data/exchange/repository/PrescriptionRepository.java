package org.thinking.logistics.data.exchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.data.exchange.domain.PrescriptionHeader;

@Repository
public interface PrescriptionRepository extends JpaRepository<PrescriptionHeader, Long> {
}