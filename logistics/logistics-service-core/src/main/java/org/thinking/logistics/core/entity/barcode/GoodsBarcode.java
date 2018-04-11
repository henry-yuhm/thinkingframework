package org.thinking.logistics.core.entity.barcode;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class GoodsBarcode extends Barcode {
}