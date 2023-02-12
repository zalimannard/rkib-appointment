package ru.zalimannard.bachelorthesisserver.favor;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavorServiceImpl implements FavorService {
    private final FavorRepository favorRepository;
    private final FavorMapper favorMapper = Mappers.getMapper(FavorMapper.class);

    @Override
    public FavorDto get(int id) {
        Favor favor = favorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Favor", "id", String.valueOf(id)));
        return favorMapper.toDto(favor);
    }

    @Override
    public List<FavorDto> list() {
        List<Favor> favorList = new ArrayList<>();
        favorRepository.findAll().forEach(favorList::add);
        return favorMapper.toDtoList(favorList);
    }
}
