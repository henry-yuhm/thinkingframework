package org.thinking.logistics.services.core.entity.dictionary;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema = "wms")
@Data
@EqualsAndHashCode(callSuper = true)
public class GoodsCategory extends Dictionary {
}