package org.thinking.logistics.services.core.domain.support;

//上架方式
public enum UpshelfMode {
    TASK(1) {
        @Override
        public String toString() {
            return "按任务";
        }
    },
    PIECE(2) {
        @Override
        public String toString() {
            return "按件数";
        }
    };

    UpshelfMode(int ordinal) {
    }
}