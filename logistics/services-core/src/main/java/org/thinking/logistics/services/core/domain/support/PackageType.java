package org.thinking.logistics.services.core.domain.support;

//包装类型
public enum PackageType {
    WHOLEPIECES(1) {
        @Override
        public String toString() {
            return "整件";
        }
    },
    REMAINDER(2) {
        @Override
        public String toString() {
            return "零货";
        }
    };

    PackageType(int ordinal) {
    }
}