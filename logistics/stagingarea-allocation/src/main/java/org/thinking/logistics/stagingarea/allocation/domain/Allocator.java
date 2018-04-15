package org.thinking.logistics.stagingarea.allocation.domain;

import org.thinking.logistics.services.core.entity.Direction;

public interface Allocator {
    void verify() throws Exception;

    void getPhysicalConfiguration() throws Exception;

    void getVirtualConfiguration() throws Exception;

    void getVirtualConfiguration(Direction direction) throws Exception;

    void getCategory() throws Exception;

    void getAvailableArea() throws Exception;

    void save();

    void allocate() throws Exception;
}