package org.thinking.logistics.services.core.domain.support;

//月台分配方式
public enum StagingareaAllocateMode {
    BY_CAPACITY(1) {
        @Override
        public String toString() {
            return "按容量";
        }
    },
    BY_QUANTITY(2) {
        @Override
        public String toString() {
            return "按数量";
        }
    };

    StagingareaAllocateMode(int ordinal) {
    }
}