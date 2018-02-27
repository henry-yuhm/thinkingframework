package org.jointown.logistics.workflow.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Embeddable;

@Embeddable
public class Style {
    @JSONField(ordinal = 1, name = "left")
    private String leftPiexl;

    @JSONField(ordinal = 2, name = "top")
    private String topPiexl;

    @JSONField(ordinal = 3, name = "width")
    private String widthPiexl;

    @JSONField(ordinal = 4, name = "height")
    private String heightPiexl;

    public Style() {
    }

    public String getLeftPiexl() {
        return leftPiexl;
    }

    public void setLeftPiexl(String leftPiexl) {
        this.leftPiexl = leftPiexl;
    }

    public String getTopPiexl() {
        return topPiexl;
    }

    public void setTopPiexl(String topPiexl) {
        this.topPiexl = topPiexl;
    }

    public String getWidthPiexl() {
        return widthPiexl;
    }

    public void setWidthPiexl(String widthPiexl) {
        this.widthPiexl = widthPiexl;
    }

    public String getHeightPiexl() {
        return heightPiexl;
    }

    public void setHeightPiexl(String heightPiexl) {
        this.heightPiexl = heightPiexl;
    }
}