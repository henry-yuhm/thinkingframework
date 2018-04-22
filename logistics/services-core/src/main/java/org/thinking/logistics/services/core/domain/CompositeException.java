package org.thinking.logistics.services.core.domain;

public class CompositeException {
    public static Exception getException(String message, Object... objects) {
        StringBuilder translatedMessage = new StringBuilder();

        if (message == null || message.isEmpty()) {
            return new Exception("必须定义基本消息内容");
        }

        //无辅助信息时直接抛出消息
        if (objects == null || objects.length == 0) {
            return new Exception(message);
        }

        //循环构造辅助信息并添加至消息中
        translatedMessage.append(message);
        for (Object object : objects) {
            translatedMessage.append(object.toString());
        }
        return new Exception(translatedMessage.toString());
    }
}