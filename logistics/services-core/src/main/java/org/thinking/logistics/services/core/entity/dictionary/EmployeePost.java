package org.thinking.logistics.services.core.entity.dictionary;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class EmployeePost extends Dictionary {
}