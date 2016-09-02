package com.platform.iot.dispatcher.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.platform.iot.dispatcher.model.Sensor;

/**
 * Created by ioan.vranau on 4/27/2016.
 */
public interface SensorRepository extends CrudRepository<Sensor, Long> {
    @Query("SELECT a FROM Sensor a WHERE a.name= ?1")
    Sensor findByName(String name);
}
