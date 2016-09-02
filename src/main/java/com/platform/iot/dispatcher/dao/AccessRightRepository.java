package com.platform.iot.dispatcher.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.platform.iot.dispatcher.model.AccessRight;

/**
 * Created by ioan.vranau on 4/27/2016.
 */
public interface AccessRightRepository extends CrudRepository<AccessRight, Long> {

    @Query("SELECT a FROM AccessRight a WHERE a.name= ?1")
    AccessRight findByName(String name);
}
