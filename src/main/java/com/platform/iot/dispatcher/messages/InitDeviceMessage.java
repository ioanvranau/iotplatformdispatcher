package com.platform.iot.dispatcher.messages;

/**
 * Created by ioan.vranau on 9/2/2016.
 */

public class InitDeviceMessage {

    private String deviceName;
    private Long deviceId;
    private String deviceIp;
    private boolean disconnect;

    public InitDeviceMessage() {
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceIp() {
        return deviceIp;
    }

    public void setDeviceIp(String deviceIp) {
        this.deviceIp = deviceIp;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public boolean isDisconnect() {
        return disconnect;
    }

    public void setDisconnect(boolean disconnect) {
        this.disconnect = disconnect;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}