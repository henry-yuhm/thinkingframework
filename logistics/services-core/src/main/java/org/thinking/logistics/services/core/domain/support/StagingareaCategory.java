package org.thinking.logistics.services.core.domain.support;

//月台类别
public enum StagingareaCategory {
    MINIATURE(1) {
        @Override
        public String toString() {
            return "小型";
        }
    },
    MEDIUM(2) {
        @Override
        public String toString() {
            return "中等";
        }
    },
    HEAVY(3) {
        @Override
        public String toString() {
            return "大型";
        }
    };

    StagingareaCategory(int ordinal) {
    }
}