package org.thinking.logistics.workflow.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Style {
    @JSONField(ordinal = 1, name = "left")
    private String leftPiexl;

    @JSONField(ordinal = 2, name = "top")
    private String topPiexl;

//    @JSONField(ordinal = 3, name = "width")
//    private String widthPiexl;

//    @JSONField(ordinal = 4, name = "height")
//    private String heightPiexl;
}