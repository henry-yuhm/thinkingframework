package org.thinking.sce.statemachine.dispatcher.domain;

import lombok.*;

import javax.persistence.*;

@Embeddable
@Data
@NoArgsConstructor
public class Style {
    @Column(name = "leftPiexl")
    private String left;

    @Column(name = "topPiexl")
    private String top;
}