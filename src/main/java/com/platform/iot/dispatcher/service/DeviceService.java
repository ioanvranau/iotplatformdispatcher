package com.platform.iot.dispatcher.service;

import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.google.common.collect.Lists;
import com.platform.iot.dispatcher.dao.DeviceRepository;
import com.platform.iot.dispatcher.model.AccessRight;
import com.platform.iot.dispatcher.model.Device;
import com.platform.iot.dispatcher.model.Location;
import com.platform.iot.dispatcher.model.Sensor;
import com.platform.iot.dispatcher.utils.IotException;
import com.platform.iot.dispatcher.utils.IpValidator;

/**
 * Created by ioan.vranau on 4/27/2016.
 */
@Component
public class DeviceService {

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    private LocationService locationService;

    @Autowired
    private AccessRightsService accessRightsService;

    public List<Device> getAllDevices() {
        return Lists.newArrayList(deviceRepository.findAll());
    }

    public Device getDeviceById(Long id) {
        return deviceRepository.findOne(id);
    }

    @Transactional
    public Device addDevice(Device device) throws UnknownHostException {
        if (device != null) {
            String deviceIp = device.getIp();
            if (deviceIp == null || StringUtils.isEmpty(deviceIp.trim())) {
                throw new IotException("No device ip provided!");
            }
            IpValidator.validate(deviceIp);

            if (device.getLocation() != null) {
                Location location = locationService.getLocation(device.getLocation().getId());
                if (location == null) {
                    locationService.addLocation(device.getLocation());
                }
            }

            final Set<AccessRight> accessRights = device.getAccessRights();
            final Set<AccessRight> validAccessRights = new HashSet<AccessRight>();
            if (accessRights != null) {
                for (AccessRight accessRight : accessRights) {
                    AccessRight existingAccessRight = accessRightsService.getAccessRightByName(accessRight.getName());
                    if (existingAccessRight != null) {
                        validAccessRights.add(existingAccessRight);
                    }
                }
            }
            device.setAccessRights(validAccessRights);
            return deviceRepository.save(device);
        } else {
            throw new IotException("No device provided!");
        }
    }

    @Transactional
    public Device updateDevice(Long deviceId, Sensor sensor) throws UnknownHostException {
        final Device deviceById = getDeviceById(deviceId);
        deviceById.getSensors().add(sensor);
        return deviceRepository.save(deviceById);
    }

    public void deleteDevice(long deviceId) {
        Device device = deviceRepository.findOne(deviceId);
        if (device != null) {
            deviceRepository.delete(deviceId);
        } else {
            throw new IotException("No device provided!");
        }
    }
}
