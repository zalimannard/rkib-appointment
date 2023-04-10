package ru.zalimannard.rkibappointmentbackend.schema.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {

    Person getPersonByUsername(String username);

    Person getPersonByPassword(String password);
}
