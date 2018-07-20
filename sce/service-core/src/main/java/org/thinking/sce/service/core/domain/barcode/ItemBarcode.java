package org.thinking.sce.service.core.domain.barcode;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.Entity;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class ItemBarcode extends Barcode {
}