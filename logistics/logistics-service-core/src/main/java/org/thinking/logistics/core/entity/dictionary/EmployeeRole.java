package org.thinking.logistics.core.entity.dictionary;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema = "wms")
public class EmployeeRole extends Dictionary {
    public EmployeeRole() {
    }
}