package org.thinking.logistics.data.exchange.domain.support;

public enum DownloadState {
    UNDOWNLOADED {
        @Override
        public String toString() {
            return "未下载";
        }
    },
    DOWNLOADED {
        @Override
        public String toString() {
            return "已下载";
        }
    },
    ALL {
        @Override
        public String toString() {
            return "全部";
        }
    };
}