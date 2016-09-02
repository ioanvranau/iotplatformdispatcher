package com.platform.iot.dispatcher.utils;

import java.util.List;
import java.util.Set;
import com.platform.iot.dispatcher.model.AccessRight;
import com.platform.iot.dispatcher.model.Device;
import com.platform.iot.dispatcher.model.Location;
import com.platform.iot.dispatcher.model.Sensor;
import com.platform.iot.dispatcher.model.Tag;

/**
 * Created by ioan.vranau on 4/27/2016.
 */
public class DeviceBuilder {

    public static Device build(String ip, String name, Location location, Set<AccessRight> accessRights, List<Tag> tags, List<Sensor> sensors) {
        return new Device(name, "phone", location, ip, accessRights, tags, sensors);
    }
}
