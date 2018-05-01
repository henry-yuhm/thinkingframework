package org.thinking.logistics.services.core.domain.support;

//调度类型
public enum DispatcherType {
    AUTOMATIC(1) {
        @Override
        public String toString() {
            return "自动";
        }
    },
    APPOINTED(2) {
        @Override
        public String toString() {
            return "指定";
        }
    };

    DispatcherType(int ordinal) {
    }
}