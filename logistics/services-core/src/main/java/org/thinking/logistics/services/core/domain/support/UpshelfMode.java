package org.thinking.logistics.services.core.domain.support;

//上架方式
public enum UpshelfMode {
    TASK("T", 1),//按任务
    PIECE("P", 2);//按件

    UpshelfMode(String name, int ordinal) {
    }
}