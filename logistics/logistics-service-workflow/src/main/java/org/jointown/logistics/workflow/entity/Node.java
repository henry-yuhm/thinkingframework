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

    @Embedded
    @JSONField(ordinal = 2)
    private Style style;

    @JSONField(ordinal = 3)
    private boolean isSelected;

    @OneToOne(fetch = FetchType.EAGER)
    @JSONField(ordinal = 4)
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

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public JpaRepositoryState getState() {
        return state;
    }

    public void setState(JpaRepositoryState state) {
        this.state = state;
    }
}