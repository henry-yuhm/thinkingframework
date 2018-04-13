package org.thinking.logistics.services.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.entity.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Owner findByNumber(String number);
}