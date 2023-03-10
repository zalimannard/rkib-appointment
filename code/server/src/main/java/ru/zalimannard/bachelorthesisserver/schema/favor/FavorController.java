package ru.zalimannard.bachelorthesisserver.schema.favor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}${application.endpoint.favor}")
@Tag(name = "Услуги")
@RequiredArgsConstructor
public class FavorController {

    private final FavorService favorService;

    @GetMapping("{id}")
    @Operation(summary = "Получение услуги")
    public FavorDto get(@PathVariable String id) {
        return favorService.read(id);
    }

    @GetMapping
    @Operation(summary = "Поиск услуг")
    public List<FavorDto> search(FavorDto exampleFavorDto,
                                 @RequestParam(value = "pageNo", defaultValue = "${application.constant.defaultPageNumber}", required = false) int pageNo,
                                 @RequestParam(value = "pageSize", defaultValue = "${application.constant.defaultPageSize}", required = false) int pageSize,
                                 @RequestParam(value = "sort", defaultValue = "${application.constant.defaultSort}", required = false) String[] sort) {
        return favorService.search(exampleFavorDto, pageNo, pageSize, sort);
    }

}
