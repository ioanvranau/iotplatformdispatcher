package com.platform.iot.dispatcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.platform.iot.dispatcher.messages.DeviceInformationThread;
import com.platform.iot.dispatcher.messages.ServerResponse;
import com.platform.iot.dispatcher.utils.Topics;

/**
 * Created by ioan.vranau on 9/2/2016.
 */
@Controller
public class DeviceController {


    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping(Topics.WS.INIT_DISPATCHER_CONNECTION)
    @SendTo(Topics.WS.INIT_DISPATCHER_RECEIVER)
    @CrossOrigin(origins = "*")
    public ServerResponse initDispatcher() throws Exception { //this method is called when @MessageMapping
        DeviceInformationThread deviceInformationThread = new DeviceInformationThread(this);
        new Thread(deviceInformationThread).start();
        return new ServerResponse("Successfully connected to the device dispatcher!"); // this is sent to @SendTo topic
    }

    public void sendMessageToTopic(String topic, String message) {
        this.template.convertAndSend(topic, new ServerResponse(message));
    }
}