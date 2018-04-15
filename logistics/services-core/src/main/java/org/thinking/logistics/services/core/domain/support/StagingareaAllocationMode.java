package org.thinking.logistics.services.core.domain.support;

//月台分配方式
public enum StagingareaAllocationMode {
    VOLUMETRIC("1", 1),//按体积
    PIECEMEAL("2", 2);//按件数

    StagingareaAllocationMode(String name, int ordinal) {
    }
}