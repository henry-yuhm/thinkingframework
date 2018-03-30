package org.jointown.logistics.core.entity.bill;

import java.util.Set;

public interface Headers {
    Set<? extends Detail> getDetails();
}