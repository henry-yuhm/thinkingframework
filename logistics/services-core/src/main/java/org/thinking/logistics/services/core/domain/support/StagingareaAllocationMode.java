package org.thinking.logistics.services.core.domain.support;

//月台分配方式
public enum StagingareaAllocationMode {
    VOLUMETRIC(1) {
        @Override
        public String toString() {
            return "按体积";
        }
    },
    PIECEMEAL(2) {
        @Override
        public String toString() {
            return "按件数";
        }
    };

    StagingareaAllocationMode(int ordinal) {
    }
}