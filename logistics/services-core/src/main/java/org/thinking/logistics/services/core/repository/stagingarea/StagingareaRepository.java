package org.thinking.logistics.services.core.repository.stagingarea;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.thinking.logistics.services.core.domain.support.BillType;
import org.thinking.logistics.services.core.domain.support.StagingareaCategory;
import org.thinking.logistics.services.core.domain.support.StagingareaType;
import org.thinking.logistics.services.core.domain.support.TakegoodsMode;
import org.thinking.logistics.services.core.entity.Direction;
import org.thinking.logistics.services.core.entity.Owner;
import org.thinking.logistics.services.core.entity.stagingarea.Stagingarea;

import javax.persistence.LockModeType;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Repository
public interface StagingareaRepository extends JpaRepository<Stagingarea, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "select sga.number from Stagingarea sga " +
        "where sga.type=:type " +
        "and sga.category=:category " +
        "and sga.locking=false " +
        "and sga.available=true " +
        "and sga.billType=:billType " +
        "and sga.takegoodsMode=:takegoodsMode " +
        "and sga.owners in :owners " +
        "and sga.direction=:direction " +
        "order by sga.number")
    LinkedList<String> acquireAvailableArea(StagingareaType type, StagingareaCategory category, BillType billType, TakegoodsMode takegoodsMode, Set<Owner> owners, Direction direction);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "select sga from Stagingarea sga " +
        "where sga.number>=:from " +
        "and sga.number<=:to " +
        "and sga.type=:type " +
        "and sga.category=:category " +
        "and sga.locking=false " +
        "and sga.available=true " +
        "and sga.billType=:billType " +
        "and sga.takegoodsMode=:takegoodsMode " +
        "and sga.owners in :owners " +
        "and sga.direction=:direction " +
        "order by sga.number")
    List<Stagingarea> acquireAvailableArea(String from, String to, StagingareaType type, StagingareaCategory category, BillType billType, TakegoodsMode takegoodsMode, Set<Owner> owners, Direction direction);
}