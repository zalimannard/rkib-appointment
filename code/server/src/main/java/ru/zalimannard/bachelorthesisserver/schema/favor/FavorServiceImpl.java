package ru.zalimannard.bachelorthesisserver.schema.favor;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.utils.Utils;
import ru.zalimannard.bachelorthesisserver.utils.mapper.MappingType;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavorServiceImpl implements FavorService {

    private final FavorRepository favorRepository;
    private final FavorMapper favorMapper;

    @Override
    public FavorDto read(String id) {
        Favor favor = favorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("${application.entityNames.favor}", id));
        return favorMapper.toDto(favor);
    }

    @Override
    public List<FavorDto> search(FavorDto filterFavorDto, int pageNo, int pageSize, String[] sort) {
        Favor filterFavor = favorMapper.toEntity(filterFavorDto, MappingType.FORCE);
        List<Sort.Order> orders = Utils.ordersByStringArray(sort);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(orders));

        List<Favor> favorList = favorRepository.search(filterFavor.getName(), pageable);
        return favorMapper.toDtoList(favorList);
    }

}
