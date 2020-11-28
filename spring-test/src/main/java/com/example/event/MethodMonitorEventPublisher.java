package com.example.event;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MethodMonitorEventPublisher {
    private List<MethodMonitorEventListener> listeners = new ArrayList<>();

    public void methodMonitor() throws InterruptedException {
        MethodMonitorEvent eventObject = new MethodMonitorEvent(this);

        publishEvent("begin", eventObject);

        // 模拟方法执行：休眠5秒钟
        TimeUnit.SECONDS.sleep(5);

        publishEvent("end", eventObject);
    }

    private void publishEvent(String status, MethodMonitorEvent event) {
        // 避免在事件处理期间，监听器被移除，这里为了安全做一个复制操作
        List<MethodMonitorEventListener> copyListeners = new ArrayList<>(listeners);
        for (MethodMonitorEventListener listener : copyListeners) {
            if ("begin".equals(status)) {
                listener.onMethodBegin(event);
            } else {
                listener.onMethodEnd(event);
            }
        }
    }

    // 省略实现
    public void addEventListener(MethodMonitorEventListener listener) {
        listeners.add(listener);
    }

    public void removeEventListener(MethodMonitorEventListener listener) {
    }

    public void removeAllListeners() {
    }

}