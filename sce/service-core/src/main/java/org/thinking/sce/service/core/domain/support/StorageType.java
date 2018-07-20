package org.thinking.sce.service.core.domain.support;

//存放类型
public enum StorageType {
    SINGLE_ITEM(1) {
        @Override
        public String toString() {
            return "单品规";
        }
    },
    SINGLE_LOT(2) {
        @Override
        public String toString() {
            return "单批号";
        }
    },
    MULTIPLE_ITEM(3) {
        @Override
        public String toString() {
            return "多品规";
        }
    };

    StorageType(int ordinal) {
    }
}