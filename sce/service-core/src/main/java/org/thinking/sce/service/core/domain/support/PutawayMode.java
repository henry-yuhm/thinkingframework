package org.thinking.sce.service.core.domain.support;

//上架方式
public enum PutawayMode {
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

    PutawayMode(int ordinal) {
    }
}