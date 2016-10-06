package com.platform.iot.dispatcher.utils;

/**
 * Created by ioan.vranau on 10/6/2016.
 */
public class Topics {
    public static final class WS {
        public static final String INIT_DISPATCHER_RECEIVER = ROOT.RECEIVER + "/initDispatcherConnection"; // this is receiver because we better distinguish them in the UI
        public static final String INIT_DISPATCHER_CONNECTION = "/initDispatcherConnection";
        public static final String TOPIC_ACC_SENSOR = ROOT.RECEIVER + "/accSensor";

        public static final class ROOT {
            public static final String RECEIVER = "/receiver";
            public static final String DESTINATION = "/destination";
            public static final String IOT_DISPATCHER_WEBSOCKET = "/iot-dispatcher-websocket";
        }
    }

    public static final class MQTT {
        public static final String IOT_ANDROID = "iotandroid";
    }
}
