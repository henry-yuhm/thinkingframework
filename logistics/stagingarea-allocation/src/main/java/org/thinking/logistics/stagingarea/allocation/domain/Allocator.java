package org.thinking.logistics.stagingarea.allocation.domain;

import org.thinking.logistics.services.core.domain.common.Direction;

public interface Allocator {
    void verify() throws Exception;

    void acquirePhysicalConfiguration() throws Exception;

    void acquireVirtualConfiguration() throws Exception;

    void acquireVirtualConfiguration(Direction direction) throws Exception;

    void acquireCategory() throws Exception;

    void acquireAvailableArea() throws Exception;

    void save();

    void allocate() throws Exception;
}