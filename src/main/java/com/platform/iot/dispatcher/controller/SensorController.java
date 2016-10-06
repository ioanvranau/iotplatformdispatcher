//package com.platform.iot.dispatcher.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import com.platform.iot.dispatcher.messages.DeviceInformationThread;
//import com.platform.iot.dispatcher.messages.InitDeviceMessage;
//import com.platform.iot.dispatcher.messages.ServerResponse;
//import com.platform.iot.dispatcher.utils.ThreadPool;
//
///**
// * Created by ioan.vranau on 9/2/2016.
// */
//@Controller
//public class SensorController {
//
//
//    @MessageMapping("/acc")
//    @SendTo("/topic/accSensor")
//    @CrossOrigin(origins = "*")
//    public ServerResponse initDispatcher(InitDeviceMessage initDeviceMessage) throws Exception { //this method is called when @MessageMapping
//
//        final String content = "";
//        return new ServerResponse(content); // this is sent to @SendTo topic
//    }
//
//}