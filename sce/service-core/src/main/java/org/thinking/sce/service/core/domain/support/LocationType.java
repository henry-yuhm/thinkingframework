package org.thinking.sce.service.core.domain.support;

//货位类型
public enum LocationType {
    NORMAL(1) {
        @Override
        public String toString() {
            return "正常";
        }
    };

    LocationType(int ordinal) {
    }
}