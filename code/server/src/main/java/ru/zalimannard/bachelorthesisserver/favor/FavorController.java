package ru.zalimannard.bachelorthesisserver.favor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.favor}")
@Tag(name = "Услуги")
@RequiredArgsConstructor
public class FavorController {
    private final FavorService favorService;

    @GetMapping("{id}")
    @Operation(summary = "Получение услуги")
    public FavorDto get(@PathVariable String id) {
        return favorService.get(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка услуг")
    public List<FavorDto> getAll() {
        return favorService.list();
    }
}