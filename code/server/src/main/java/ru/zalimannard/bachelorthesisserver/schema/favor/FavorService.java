package ru.zalimannard.bachelorthesisserver.schema.favor;

import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface FavorService {

    FavorDto read(String id);

    List<FavorDto> search(FavorDto favorDto, int pageNo, int pageSize, String[] sort);

}
