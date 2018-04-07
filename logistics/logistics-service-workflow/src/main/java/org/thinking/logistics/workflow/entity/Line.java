package org.thinking.logistics.workflow.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.statemachine.data.jpa.JpaRepositoryTransition;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(Line.LinePk.class)
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

    public Line() {
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public JpaRepositoryTransition getTransition() {
        return transition;
    }

    public void setTransition(JpaRepositoryTransition transition) {
        this.transition = transition;
    }

    public static class LinePk implements Serializable {
        private String source;

        private String target;

        public LinePk() {
        }

        @Override
        public int hashCode() {
            return this.source.hashCode() + this.target.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null) {
                return false;
            }

            if (this.getClass() != obj.getClass()) {
                return false;
            }

            LinePk linePk = (LinePk) obj;

            return (this.source.equals(linePk.source) && this.target.equals(linePk.target));
        }
    }
}