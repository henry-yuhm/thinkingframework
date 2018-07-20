package org.thinking.sce.service.core.domain.command;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.Entity;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper = true)
public class PilerCommand extends Command {
}