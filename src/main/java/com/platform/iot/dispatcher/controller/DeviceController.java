package com.platform.iot.dispatcher.controller;

/**
 * Created by ioan.vranau on 1/4/2016.
 */

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.google.common.collect.Lists;

@RestController
public class DeviceController {

    private Logger log = Logger.getLogger(DeviceController.class);

    @RequestMapping("/device")
    public
    @ResponseBody
    List<Device> getAllDevices() {
        return Lists.newArrayList(new Device());
    }

    private class Device {
    }
}