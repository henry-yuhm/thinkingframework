package org.thinking.sce.service.core.domain.container;

import lombok.*;
import org.hibernate.annotations.*;
import org.thinking.sce.service.core.domain.document.PickingList;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class Totebox extends Container {
    @OneToOne(fetch = FetchType.LAZY)
    private PickingList pickingList;//任务
}