package me.diferreira.apichamplol.application;

import me.diferreira.apichamplol.domain.excepion.ChampionNotFoundException;
import me.diferreira.apichamplol.domain.model.Champions;
import me.diferreira.apichamplol.domain.ports.ChampionsRepository;

import java.util.List;

public record AskChampionUseCase(ChampionsRepository repository) {
    public String askChampion(Long championId, String question) {
        Champions champion = repository.findById(championId)
                .orElseThrow(() -> new ChampionNotFoundException(championId));

        String championContext = champion.generateContextQuestion(question);

        // TODO: Evoluir a lógica de negócio para considerar a integração com IAs Generativas.
        return championContext;
    }
}
