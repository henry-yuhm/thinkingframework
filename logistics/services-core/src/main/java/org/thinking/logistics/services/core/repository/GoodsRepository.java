package org.thinking.logistics.services.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.entity.Goods;
import org.thinking.logistics.services.core.entity.Owner;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long> {
    Goods findByOwnerAndNumber(Owner owner, String number);
}