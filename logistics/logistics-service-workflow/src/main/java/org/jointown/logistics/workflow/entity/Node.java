package org.jointown.logistics.workflow.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.statemachine.data.jpa.JpaRepositoryState;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Node implements Serializable {
    @Id
    @Column(length = 50)
    @JSONField
    private String id;

    @JSONField(ordinal = 1)
    private String label;

    @Transient
    @JSONField(ordinal = 2)
    private boolean current;

    @JSONField(ordinal = 3)
    private String shape;

    @Embedded
    @JSONField(ordinal = 4)
    private Style style;

    @OneToOne(fetch = FetchType.EAGER)
    @JSONField(ordinal = 5)
    private JpaRepositoryState state;

    public Node() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public JpaRepositoryState getState() {
        return state;
    }

    public void setState(JpaRepositoryState state) {
        this.state = state;
    }
}