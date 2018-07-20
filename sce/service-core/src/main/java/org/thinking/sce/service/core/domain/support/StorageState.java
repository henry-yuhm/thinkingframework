package org.thinking.sce.service.core.domain.support;

//存放状态
public enum StorageState {
    QUALIFICATION(1) {
        @Override
        public String toString() {
            return "合格";
        }
    },
    DISQUALIFICATION(2) {
        @Override
        public String toString() {
            return "不合格";
        }
    };

    StorageState(int ordinal) {
    }
}