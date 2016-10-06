package com.platform.iot.dispatcher.messages;

/**
 * Created by ioan.vranau on 9/2/2016.
 */

public class InitDeviceMessage {

    private String deviceId;

    public InitDeviceMessage() {
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}