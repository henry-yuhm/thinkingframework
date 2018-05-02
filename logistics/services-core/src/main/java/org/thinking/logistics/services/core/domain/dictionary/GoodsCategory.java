package org.thinking.logistics.services.core.domain.dictionary;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class GoodsCategory extends Dictionary {
}