package org.thinking.logistics.workflow.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
public class Style {
    @Column(name = "leftPiexl")
    private String left;

    @Column(name = "topPiexl")
    private String top;
}