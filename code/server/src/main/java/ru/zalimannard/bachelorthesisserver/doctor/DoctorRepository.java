package ru.zalimannard.bachelorthesisserver.doctor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Integer> {

}
