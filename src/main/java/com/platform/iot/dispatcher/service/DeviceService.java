package com.platform.iot.dispatcher.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.common.collect.Lists;
import com.platform.iot.dispatcher.dao.DeviceRepository;
import com.platform.iot.dispatcher.model.Device;
import com.platform.iot.dispatcher.utils.IotException;

/**
 * Created by ioan.vranau on 4/27/2016.
 */
@Component
public class DeviceService {

    @Autowired
    DeviceRepository deviceRepository;

    public List<Device> getAllDevices() {
        return Lists.newArrayList(deviceRepository.findAll());
    }

}
