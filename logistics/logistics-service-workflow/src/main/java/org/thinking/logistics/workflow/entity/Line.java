package org.thinking.logistics.workflow.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.statemachine.data.jpa.JpaRepositoryTransition;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(Line.LinePk.class)
@Data
public class Line implements Serializable {
    @Id
    @Column(length = 50)
    @JSONField(ordinal = 1)
    private String source;

    @Id
    @Column(length = 50)
    @JSONField(ordinal = 2)
    private String target;

    @JSONField(ordinal = 3)
    private String label;

    @OneToOne
    @JSONField(ordinal = 4)
    private JpaRepositoryTransition transition;

    @Data
    public static class LinePk implements Serializable {
        private String source;

        private String target;
    }
}