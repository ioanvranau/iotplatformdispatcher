package com.platform.iot.dispatcher.controller;

/**
 * Created by ioan.vranau on 1/4/2016.
 */

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.google.common.collect.Lists;
import com.platform.iot.dispatcher.model.Device;
import com.platform.iot.dispatcher.service.DeviceService;

@RestController
public class DeviceController {

    private Logger log = Logger.getLogger(DeviceController.class);

    @Autowired
    private DeviceService deviceService;

    @RequestMapping("/device")
    public
    @ResponseBody
    List<Device> getAllDevices() {
        return Lists.newArrayList();
    }


}