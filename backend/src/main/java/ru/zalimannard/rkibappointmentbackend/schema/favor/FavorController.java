package ru.zalimannard.rkibappointmentbackend.schema.favor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.favor}")
@Tag(name = "Услуга")
@RequiredArgsConstructor
public class FavorController {

    private final FavorService favorService;

    @GetMapping("{id}")
    @Operation(summary = "Получение услуги")
    public FavorDto get(@PathVariable String id) {
        return favorService.read(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка услуг")
    public List<FavorDto> search(FavorDto exampleFavorDto,
                                 @RequestParam(value = "sort",
                                         defaultValue = "${application.constant.defaultSort}",
                                         required = false) String[] sort,
                                 @RequestParam(value = "pageSize") int pageSize,
                                 @RequestParam(value = "pageNumber") int pageNumber) {
        return favorService.search(exampleFavorDto, sort, pageSize, pageNumber);
    }

    @PostMapping
    @Operation(summary = "Создание услуги")
    @ResponseStatus(HttpStatus.CREATED)
    public FavorDto post(@RequestBody FavorDto favorDto) {
        return favorService.create(favorDto);
    }

    @PutMapping("{id}")
    @Operation(summary = "Обновление услуги")
    public FavorDto put(@PathVariable String id,
                        @RequestBody FavorDto favorDto) {
        return favorService.update(id, favorDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление услуги")
    public void delete(@PathVariable String id) {
        favorService.delete(id);
    }

}