package org.thinking.logistics.services.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Owner findByNo(String no);
}