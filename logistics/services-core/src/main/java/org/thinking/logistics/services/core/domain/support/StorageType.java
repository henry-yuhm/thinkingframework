package org.thinking.logistics.services.core.domain.support;

//存放类型
public enum StorageType {
    SINGLE_GOODS(1) {
        @Override
        public String toString() {
            return "单品规";
        }
    },
    SINGLE_BATCH(2) {
        @Override
        public String toString() {
            return "单批号";
        }
    },
    MULTIPLE_GOODS(3) {
        @Override
        public String toString() {
            return "多品规";
        }
    };

    StorageType(int ordinal) {
    }
}