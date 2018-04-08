package org.thinking.logistics.dispatcher.domain;

public interface BillDispatcher {
    void arrangeWave() throws Exception;

    void cancelWave() throws Exception;

    void modifyWave() throws Exception;

    void releaseWave() throws Exception;

    void releaseBill() throws Exception;
}