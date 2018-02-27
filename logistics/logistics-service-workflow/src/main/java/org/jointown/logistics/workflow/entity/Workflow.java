package org.jointown.logistics.workflow.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Workflow implements Serializable {
    @Id
    @Column(length = 50)
    @JSONField
    private String id;

    @JSONField(ordinal = 1)
    private String name;

    @JSONField(ordinal = 2)
    private String kind;

    @ManyToMany(fetch = FetchType.EAGER)
    @JSONField(ordinal = 3)
    private Set<Node> nodes;

    @ManyToMany(fetch = FetchType.EAGER)
    @JSONField(ordinal = 4)
    private Set<Line> lines;

    public Workflow() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(LinkedHashSet<Node> nodes) {
        this.nodes = nodes;
    }

    public Set<Line> getLines() {
        return lines;
    }

    public void setLines(LinkedHashSet<Line> lines) {
        this.lines = lines;
    }
}