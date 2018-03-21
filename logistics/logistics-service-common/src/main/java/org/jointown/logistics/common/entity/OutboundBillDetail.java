package org.jointown.logistics.common.entity;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;

@Entity
@AssociationOverrides({
        @AssociationOverride(name = "parentBillDetail", foreignKey = @ForeignKey(name = "fk_obd_parent_bd"))
})
public class OutboundBillDetail extends BusinessBillDetail {
}