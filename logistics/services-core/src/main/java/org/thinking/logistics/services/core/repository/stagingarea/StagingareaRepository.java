package org.thinking.logistics.services.core.repository.stagingarea;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.Direction;
import org.thinking.logistics.services.core.domain.Owner;
import org.thinking.logistics.services.core.domain.stagingarea.Stagingarea;
import org.thinking.logistics.services.core.domain.support.BillType;
import org.thinking.logistics.services.core.domain.support.StagingareaCategory;
import org.thinking.logistics.services.core.domain.support.StagingareaType;
import org.thinking.logistics.services.core.domain.support.TakegoodsMode;
import org.thinking.logistics.services.core.repository.DomainRepository;

import javax.persistence.LockModeType;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Repository
public interface StagingareaRepository extends DomainRepository<Stagingarea, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "select sga.no from Stagingarea sga " +
        "where sga.type=:type " +
        "and sga.category=:category " +
        "and sga.locking=false " +
        "and sga.available=true " +
        "and sga.billType=:billType " +
        "and sga.takegoodsMode=:takegoodsMode " +
        "and sga.owners in :owners " +
        "and sga.direction=:direction " +
        "order by sga.no")
    LinkedList<String> acquireAvailableArea(StagingareaType type, StagingareaCategory category, BillType billType, TakegoodsMode takegoodsMode, Set<Owner> owners, Direction direction);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "select sga from Stagingarea sga " +
        "where sga.no>=:from " +
        "and sga.no<=:to " +
        "and sga.type=:type " +
        "and sga.category=:category " +
        "and sga.locking=false " +
        "and sga.available=true " +
        "and sga.billType=:billType " +
        "and sga.takegoodsMode=:takegoodsMode " +
        "and sga.owners in :owners " +
        "and sga.direction=:direction " +
        "order by sga.no")
    List<Stagingarea> acquireAvailableArea(String from, String to, StagingareaType type, StagingareaCategory category, BillType billType, TakegoodsMode takegoodsMode, Set<Owner> owners, Direction direction);
}