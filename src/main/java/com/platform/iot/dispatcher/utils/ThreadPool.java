package com.platform.iot.dispatcher.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.platform.iot.dispatcher.messages.DeviceInformationThread;

/**
 * Created by ioan.vranau on 9/3/2016.
 */
public class ThreadPool {
    public final static Map<String, DeviceInformationThread> THREAD_MAP;
    static {
        THREAD_MAP = new HashMap<>();

    }
}
