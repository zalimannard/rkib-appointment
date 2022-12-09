package ru.zalimannard.bachelorthesisserver.application.status;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationStatusRepository extends CrudRepository<ApplicationStatus, Integer> {

}
