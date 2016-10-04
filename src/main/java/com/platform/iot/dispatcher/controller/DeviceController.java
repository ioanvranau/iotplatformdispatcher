package com.platform.iot.dispatcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.platform.iot.dispatcher.messages.DeviceInformationThread;
import com.platform.iot.dispatcher.messages.InitDeviceMessage;
import com.platform.iot.dispatcher.messages.ServerResponse;
import com.platform.iot.dispatcher.utils.ThreadPool;

/**
 * Created by ioan.vranau on 9/2/2016.
 */
@Controller
public class DeviceController {


    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/init")
    @SendTo("/topic/initDevices")
    @CrossOrigin(origins = "*")
    public ServerResponse initDevice(InitDeviceMessage initDeviceMessage) throws Exception { //this method is called when @MessageMapping

        final String content;
        if (initDeviceMessage.isDisconnect()) {
            final DeviceInformationThread deviceInformationThread = ThreadPool.THREAD_MAP.get(initDeviceMessage.getDeviceId());
            if (deviceInformationThread != null) {
                deviceInformationThread.stop();
                ThreadPool.THREAD_MAP.remove(initDeviceMessage.getDeviceId());
                content = "Disconnected from real device: " + initDeviceMessage.getDeviceId();
            } else {
                content = "Cannot disconnect device!";
            }
        } else {
            if (connectToRealDevice(initDeviceMessage)) {
                content = "Successfully connected to the real device: " + initDeviceMessage.getDeviceId() + "!";

                DeviceInformationThread deviceInformationThread = new DeviceInformationThread(this);
                ThreadPool.THREAD_MAP.put(initDeviceMessage.getDeviceId(), deviceInformationThread);
                new Thread(deviceInformationThread).start();
            } else {
                content = "Failed to connect to real device: " + initDeviceMessage.getDeviceName();
            }
        }

        return new ServerResponse(content); // this is sent to @SendTo topic
    }

    private boolean connectToRealDevice(InitDeviceMessage initDeviceMessage) {
        return true;
    }

    public void sendMessageToTopic(String topic, String message) {
        this.template.convertAndSend(topic, new ServerResponse(message));
    }
}