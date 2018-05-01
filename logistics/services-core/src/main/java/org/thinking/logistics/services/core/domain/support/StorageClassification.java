package org.thinking.logistics.services.core.domain.support;

//存储分类
public enum StorageClassification {
    ALL(1) {
        @Override
        public String toString() {
            return "全部";
        }
    },
    REMAINDER_ONLY(2) {
        @Override
        public String toString() {
            return "纯零";
        }
    };

    StorageClassification(int ordinal) {
    }
}