package me.diferreira.apichamplol.adapters.in;

import io.swagger.v3.oas.annotations.tags.Tag;
import me.diferreira.apichamplol.application.ListChampionsUseCase;
import me.diferreira.apichamplol.domain.model.Champions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Campeões", description = "Endpoints de dominio de campeões do LOL.")
@RestController
@RequestMapping("/champions")
public record ListChampionsRestController(ListChampionsUseCase useCase) {

    @GetMapping
    public List<Champions> findAllChampions() {
        return useCase.findAll();
    }
}
