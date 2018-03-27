package org.jointown.logistics.core.entity.support;

public enum TaskMode {
    LABEL("1", 1),//标签作业
    PAPER("2", 2),//纸单作业
    PDA("3", 3),//PDA作业
    DPS("4", 4),//DPS作业
    TPC("5", 5),//平板作业
    PILER("6", 6);//堆垛机作业

    TaskMode(String name, int ordinal) {
    }
}