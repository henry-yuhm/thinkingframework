package org.thinking.logistics.core.domain.support;

public enum OutboundStage {
    CREATED("00", 0),
    INITIALIZED("C1", 11),
    ARRANGED("D1", 21),
    RELEASED("D2", 22),
    STAGINGAREA_ALLOCATED("F1", 31),
    SUSPENDED("F2", 32),
    RESEND("F3", 33),
    BATCH_ALLOCATED("F4", 34),
    PACKED("F5", 35),
    SPLIT("F6", 36),
    TASK_READY("J1", 41),
    WORKING("J2", 42),
    TASK_COMPLETE("N3", 51),
    RECHECKING("W1", 61),
    RECHECK_COMPLETE("W2", 62),
    TERMINATED("ZZ", 99);

    OutboundStage(String name, int ordinal) {
    }
}