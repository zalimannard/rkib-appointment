package ru.zalimannard.bachelorthesisserver.favor;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Example;
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
    public FavorDto get(String id) {
        Favor favor = favorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Favor", "id", id));
        return favorMapper.toDto(favor);
    }

    @Override
    public List<FavorDto> list(FavorDto exampleFavorDto) {
        Favor exampleFavor = favorMapper.toEntity(exampleFavorDto);
        List<Favor> favorList = new ArrayList<>(favorRepository.findAll(Example.of(exampleFavor)));
        return favorMapper.toDtoList(favorList);
    }
}
