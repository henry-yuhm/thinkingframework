package org.thinking.logistics.core.entity.dictionary;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema = "wms")
public class GoodsCategory extends Dictionary {
    public GoodsCategory() {
    }
}