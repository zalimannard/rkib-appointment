package ru.zalimannard.bachelorthesisserver.favor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavorRepository extends CrudRepository<Favor, String> {

}
