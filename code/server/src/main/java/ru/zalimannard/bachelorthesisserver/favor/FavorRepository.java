package ru.zalimannard.bachelorthesisserver.favor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavorRepository extends JpaRepository<Favor, String> {

}
