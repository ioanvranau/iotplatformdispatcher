package com.platform.iot.dispatcher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.platform.iot.dispatcher.dao.LocationRepository;
import com.platform.iot.dispatcher.model.Location;
import com.platform.iot.dispatcher.utils.IotException;

/**
 * Created by ioan.vranau on 4/27/2016.
 */
@Component
public class LocationService {

    @Autowired
    LocationRepository locationRepository;

    public Location getLocation(Long id) {
        return locationRepository.findOne(id);
    }

    public Location addLocation(Location location) {
        if (location != null) {
            return locationRepository.save(location);
        } else {
            throw new IotException("No location provided!");
        }
    }

    public void deleteLocation(long locationId) {
        Location location = locationRepository.findOne(locationId);
        if (location != null) {
            locationRepository.delete(locationId);
        } else {
            throw new IotException("No location provided!");
        }
    }
}
