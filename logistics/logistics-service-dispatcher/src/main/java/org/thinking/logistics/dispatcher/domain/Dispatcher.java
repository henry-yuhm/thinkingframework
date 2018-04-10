package org.thinking.logistics.dispatcher.domain;

public interface Dispatcher {
    void arrangeWave() throws Exception;

    void cancelWave() throws Exception;

    void modifyWave() throws Exception;

    void releaseWave() throws Exception;

    void releaseBill() throws Exception;
}