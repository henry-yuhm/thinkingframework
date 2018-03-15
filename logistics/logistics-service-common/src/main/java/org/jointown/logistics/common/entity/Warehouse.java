package org.jointown.logistics.common.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Warehouse implements Serializable {
    @Id
    private long id;
}