package org.thinking.sce.service.core.domain.dictionary;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.Entity;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class EmployeeRole extends Dictionary {
}