package org.jointown.logistics.core.entity.parameter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class EnumParameter extends Parameter {
    @Column(nullable = false)
    private String value;//值

    @Embedded
    private Set<Range> ranges = new LinkedHashSet<>();//值域

    public EnumParameter() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Set<Range> getRanges() {
        return ranges;
    }

    public void setRanges(Set<Range> ranges) {
        this.ranges = ranges;
    }

    @Embeddable
    public static class Range {
        @Column(nullable = false)
        private String warehouse;//仓库

        @Column(nullable = false)
        private String value;//值

        public String getWarehouse() {
            return warehouse;
        }

        public void setWarehouse(String warehouse) {
            this.warehouse = warehouse;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
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

            Range range = (Range) obj;

            return (this.warehouse.equalsIgnoreCase(range.warehouse));
        }
    }
}