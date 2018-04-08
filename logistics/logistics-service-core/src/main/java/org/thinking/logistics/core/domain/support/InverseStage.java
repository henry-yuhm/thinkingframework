package org.thinking.logistics.core.domain.support;

public enum InverseStage {
    DISPATCHING("D", 1),
    EXECUTING("E", 2),
    SUSPENDING("S", 3),
    PICKING("P", 4),
    RECHECKING("R", 5);

    InverseStage(String name, int ordinal) {
    }
}