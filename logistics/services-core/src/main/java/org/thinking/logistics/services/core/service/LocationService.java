package org.thinking.logistics.services.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thinking.logistics.services.core.domain.common.Location;
import org.thinking.logistics.services.core.domain.common.QLocation;
import org.thinking.logistics.services.core.repository.DomainRepository;

import javax.persistence.EntityManager;

@Service
public class LocationService extends DomainService<QLocation, Location, Long> {
    @Autowired
    public LocationService(EntityManager entityManager, DomainRepository<Location, Long> repository) {
        super(entityManager, repository, QLocation.location);
    }
}