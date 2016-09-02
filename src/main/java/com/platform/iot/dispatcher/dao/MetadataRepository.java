package com.platform.iot.dispatcher.dao;

import org.springframework.data.repository.CrudRepository;
import com.platform.iot.dispatcher.model.Metadata;

/**
 * Created by ioan.vranau on 4/27/2016.
 */
public interface MetadataRepository extends CrudRepository<Metadata, Long> {
}