package org.thinking.logistics.resource.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

/**
 * Created by Henry on 2017/5/27.
 */
@Entity
@IdClass(DeliveryList.DeliveryListPk.class)
@Data
public class DeliveryList {
    @Id
    @Column(nullable = false)
    @JSONField(ordinal = 1)
    private String corporationNo;

    @Id
    @Column(nullable = false)
    @JSONField(ordinal = 2)
    private String billHeaderId;

    @Column(nullable = false)
    @JSONField(ordinal = 3)
    private String fileName;

    @Data
    public static class DeliveryListPk implements Serializable {
        private String corporationNo;

        private String billHeaderId;
    }
}