package org.jointown.logistics.common.entity.support;

public enum AppendixSign {
    ORIGINAL("1", 1),//原始指令
    APPENDING("2", 2),//正在追加
    APPEND("3", 3),//追加指令
    DISAPPEND("4", 4);//不追加

    AppendixSign(String name, int ordinal) {
    }
}