package org.thinking.logistics.services.core.entity.task;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class WholeTask extends Task {
}