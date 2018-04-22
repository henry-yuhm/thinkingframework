package org.thinking.logistics.services.core.entity.command;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class PilerCommand extends Command {
}