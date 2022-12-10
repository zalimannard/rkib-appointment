package ru.zalimannard.bachelorthesisserver.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends CrudRepository<Service, Integer> {

}
