package org.thinking.logistics.services.core.entity.dictionary;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema = "lmis")
@Data
@EqualsAndHashCode(callSuper = true)
public class EmployeeRole extends Dictionary {
}