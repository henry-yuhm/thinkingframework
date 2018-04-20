package org.thinking.logistics.data.exchange.domain.support;

public enum DownloadState {
    未下载("0", 0),
    已下载("1", 1),
    全部("2", 2);

    DownloadState(String name, int ordinal) {
    }
}