package com.platform.iot.dispatcher.utils;

/**
 * Created by ioan.vranau on 4/28/2016.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.platform.iot.dispatcher.dao.AccessRightRepository;
import com.platform.iot.dispatcher.dao.DeviceRepository;
import com.platform.iot.dispatcher.dao.LocationRepository;
import com.platform.iot.dispatcher.dao.SensorRepository;
import com.platform.iot.dispatcher.dao.TagRepository;
import com.platform.iot.dispatcher.model.AccessRight;
import com.platform.iot.dispatcher.model.Device;
import com.platform.iot.dispatcher.model.Location;
import com.platform.iot.dispatcher.model.Metadata;
import com.platform.iot.dispatcher.model.Sensor;
import com.platform.iot.dispatcher.model.Tag;

@Component
public class DeviceLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private AccessRightRepository accessRightRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private SensorRepository sensorRepository;


    private Logger log = Logger.getLogger(DeviceLoader.class);

    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Location location = new Location(46.77224125123537, 23.5853773355484);
        Location location1 = new Location(46.76942602027047, 23.59038233757019);
        locationRepository.save(location);
        locationRepository.save(location1);

        Set<AccessRight> accessRights = new HashSet<AccessRight>();
        accessRights.add(new AccessRight("public"));
        accessRights.add(new AccessRight("private"));
        accessRights.add(new AccessRight("admin"));
        accessRightRepository.save(accessRights);

        List<Tag> tags = new ArrayList<Tag>();
        tags.add(new Tag("mobile"));
        tags.add(new Tag("android"));
        tags.add(new Tag("smartphone"));

        final List<Metadata> metadata1 = Collections.singletonList(new Metadata("metadataName", "metadataValue"));
        final List<Metadata> metadata2 = Collections.singletonList(new Metadata("metadataName1", "metadataValue1"));

        final Sensor sensor = new Sensor("accelerometer", "speed", metadata1);
        sensorRepository.save(sensor);
        Device device1 = DeviceBuilder.build("localhost", "My phone", location, accessRights, tags,
                Collections.singletonList(sensor));
        deviceRepository.save(device1);

        log.info("Saved device - name: " + device1.getName());

        final Sensor sensor1 = new Sensor("accelerometer1", "speed1", metadata2);
        sensorRepository.save(sensor1);
        Device device2 = DeviceBuilder.build("127.0.0.1", "My new phone", location1, accessRights, tags,
                Collections.singletonList(sensor1));
        log.info("--------- " + device2);
        deviceRepository.save(device2);
        log.info("Saved device - name: " + device2.getName());
    }
}